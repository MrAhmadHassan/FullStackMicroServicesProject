package com.fitness.userservice.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Email should not be blank")
    @NotEmpty(message = "Email should not be empty")
    private String email;
    @NotBlank(message = "password is required")
    @Size(min = 6,message = "password must be 6 character length")
    private String password;
    private String firstName;
    private String lastName;
}
