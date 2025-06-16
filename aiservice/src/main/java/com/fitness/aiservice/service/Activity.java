package com.fitness.aiservice.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@Builder
public class Activity {
    private String id;
    private String type;
    private String userId;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startAt;
    private Map<String,Object> additionalMetrices;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
