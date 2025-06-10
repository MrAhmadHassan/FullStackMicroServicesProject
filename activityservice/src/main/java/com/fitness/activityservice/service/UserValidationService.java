package com.fitness.activityservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class UserValidationService {

    @Autowired
    public WebClient userServiceWebClient;

    public boolean validateUser(String userId){
        try{
            return userServiceWebClient
                    .get()
                    .uri("api/users/{userId}/validate",userId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
        }
        catch (WebClientResponseException e){
            if(e.getStatusCode()== HttpStatus.NOT_FOUND){
                throw new RuntimeException("User not found with this userId "+userId);
            } else if (e.getStatusCode()==HttpStatus.BAD_REQUEST) {
                throw new RuntimeException("Invalid Request "+userId);
            }
        }

        return false;
    }


}
