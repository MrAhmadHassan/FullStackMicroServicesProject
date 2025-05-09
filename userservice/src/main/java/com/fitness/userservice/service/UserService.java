package com.fitness.userservice.service;


import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.model.User;
import com.fitness.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public UserResponse getUserProfile(String userId) {

        User userInDb = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User Not Found"));
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(userInDb.getEmail());
        userResponse.setPassword(userInDb.getPassword());
        userResponse.setId(userInDb.getId());
        userResponse.setLastName(userInDb.getLastName());
        userResponse.setFirstName(userInDb.getFirstName());
        userResponse.setCreatedAt(userInDb.getCreatedAt());
        userResponse.setUpdatedAt(userInDb.getUpdatedAt());


        return userResponse;
    }

    public UserResponse register(RegisterRequest registerRequest) {

            if(userRepository.existsByEmail(registerRequest.getEmail())){
                throw new RuntimeException("User already exists");
            }

            User user = new User();
            user.setEmail(registerRequest.getEmail());
            user.setPassword(registerRequest.getPassword());
            user.setFirstName(registerRequest.getFirstName());
            user.setLastName(registerRequest.getLastName());
            User savedUser = userRepository.save(user);

            UserResponse userResponse = new UserResponse();
            userResponse.setEmail(savedUser.getEmail());
            userResponse.setPassword(savedUser.getPassword());
            userResponse.setId(savedUser.getId());
            userResponse.setLastName(savedUser.getLastName());
            userResponse.setFirstName(savedUser.getFirstName());
            userResponse.setCreatedAt(savedUser.getCreatedAt());
            userResponse.setUpdatedAt(savedUser.getUpdatedAt());

        return userResponse;

    }
}
