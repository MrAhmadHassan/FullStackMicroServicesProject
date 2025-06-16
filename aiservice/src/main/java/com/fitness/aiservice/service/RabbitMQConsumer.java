package com.fitness.aiservice.service;

import com.fitness.aiservice.config.RabbitMQConfig;
import com.fitness.aiservice.model.Recommendation;
import com.fitness.aiservice.repository.RecommendationRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQConsumer {

    @Autowired
    ActivityAIService activityAIService;

    @Autowired
    RecommendationRepository recommendationRepository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receiveMessage(Activity activity) {
        log.info("Processing activity with Id: {}",activity.getUserId());
        Recommendation recommendation = activityAIService.generateRecommendation(activity);
        recommendationRepository.save(recommendation);
    }
}
