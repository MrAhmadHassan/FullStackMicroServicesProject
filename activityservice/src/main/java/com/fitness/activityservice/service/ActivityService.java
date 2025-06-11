package com.fitness.activityservice.service;

import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.model.Activity;
import com.fitness.activityservice.repository.ActivityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ActivityService {

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    UserValidationService userValidationService;
    @Autowired
    RabbitMQProducer rabbitMQProducer;

    public ActivityResponse trackActivity(ActivityRequest activityRequest) {

        boolean isUserExists = userValidationService.validateUser(activityRequest.getUserId());
        if(!isUserExists){
            throw new RuntimeException("User with this Id not exists "+activityRequest.getUserId());
        }

        Activity activity = new Activity();
        activity.setUserId(activityRequest.getUserId());
        activity.setActivityType(activityRequest.getActivityType());
        activity.setDuration(activityRequest.getDuration());
        activity.setStartAt(activityRequest.getStartAt());
        activity.setAdditionalMatrices(activityRequest.getAdditionalMatrices());
        activity.setCaloriesBurned(activityRequest.getCaloriesBurned());
        Activity savedActivity = activityRepository.save(activity);

        try{
            rabbitMQProducer.sendMessage(savedActivity);
        }catch (Exception e){
            log.error("Failed to publish activity to rabbitMQ: ",e.getMessage());
        }

        return toResponse(savedActivity);
    }

    private ActivityResponse toResponse(Activity savedActivity) {
        ActivityResponse activityResponse = new ActivityResponse();
        activityResponse.setActivityType(savedActivity.getActivityType());
        activityResponse.setId(savedActivity.getId());
        activityResponse.setUserId(savedActivity.getUserId());
        activityResponse.setDuration(savedActivity.getDuration());
        activityResponse.setStartAt(savedActivity.getStartAt());
        activityResponse.setCaloriesBurned(savedActivity.getCaloriesBurned());
        activityResponse.setAdditionalMatrices(savedActivity.getAdditionalMatrices());
        activityResponse.setCreatedAt(savedActivity.getCreatedAt());
        activityResponse.setUpdatedAt(savedActivity.getUpdatedAt());
        return activityResponse;
    }

    public List<ActivityResponse> getUserActivities(String userId) {
        List<Activity> activities = activityRepository.findByUserId(userId);

        return activities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ActivityResponse getActivity(String activityId) {
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(()->new RuntimeException("Activity not exists"));

        return toResponse(activity);
    }
}
