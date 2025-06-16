package com.fitness.aiservice.model;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "recommendation")
@Data
@Builder
public class Recommendation {

    @Id
    private String id;
    private String recommendation;
    private String userId;
    private String activityId;
    private String activityType;
    private List<String>  improvements;
    private List<String> safety;
    private List<String> suggestions;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime lastUpdatedAt;
}
