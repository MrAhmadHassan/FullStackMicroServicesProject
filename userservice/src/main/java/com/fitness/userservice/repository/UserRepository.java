package com.fitness.userservice.repository;

import com.fitness.userservice.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    boolean existsByEmail(String email);
}
