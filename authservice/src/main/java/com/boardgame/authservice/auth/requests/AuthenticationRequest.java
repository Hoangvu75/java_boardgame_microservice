package com.boardgame.authservice.auth.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
public class AuthenticationRequest {

    @NotBlank(message = "Email is empty")
    private String email;

    @NotBlank(message = "Password is empty")
    private String password;
}