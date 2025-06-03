package com.fitness.activityservice.service;

import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.model.Activity;
import com.fitness.activityservice.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    @Autowired
    ActivityRepository activityRepository;

    public ActivityResponse trackActivity(ActivityRequest activityRequest) {
        Activity activity = new Activity();
        activity.setUserId(activityRequest.getUserId());
        activity.setActivityType(activityRequest.getActivityType());
        activity.setDuration(activityRequest.getDuration());
        activity.setStartAt(activityRequest.getStartAt());
        activity.setAdditionalMatrices(activityRequest.getAdditionalMatrices());
        activity.setCaloriesBurned(activityRequest.getCaloriesBurned());
        Activity savedActivity = activityRepository.save(activity);
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
        return activityRepository.findByUserId(userId);
    }
}
