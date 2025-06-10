package com.fitness.aiservice.service;

import com.fitness.aiservice.dto.RecommendationResponse;
import com.fitness.aiservice.model.Recommendation;
import com.fitness.aiservice.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecommendationService {

    @Autowired
    RecommendationRepository recommendationRepository;

    public List<Recommendation> getUserRecommendations(String userId) {

        return recommendationRepository.findByUserId(userId);
    }

    public List<Recommendation> getActivityRecommendations(String activityId) {

        return recommendationRepository.findByActivityId(activityId);
    }

}
