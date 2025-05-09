package com.fitness.userservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class UserResponse {

    private String Id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
