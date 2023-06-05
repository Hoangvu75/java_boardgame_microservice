package com.boardgame.authservice.auth.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardgame.authservice.auth.requests.AuthenticationRequest;
import com.boardgame.authservice.auth.requests.RegisterRequest;
import com.boardgame.authservice.auth.services.AuthService;
import com.boardgame.authservice.exceptions.CustomException.ValidationException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private final AuthService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(
            @Valid @RequestBody RegisterRequest request,
            BindingResult bindingResult) {
        checkForValidation(bindingResult);
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(
            @Valid @RequestBody AuthenticationRequest request,
            BindingResult bindingResult) {
        checkForValidation(bindingResult);
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/getManagerData")
    public ResponseEntity<Object> getManagerData(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        return ResponseEntity.ok(authenticationService.getManagerData(authorization));
    }

    private void checkForValidation(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorDetails = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            String errorDetailsString = String.join("; ", errorDetails);
            throw new ValidationException("Invalid data: " + errorDetailsString);
        }
    }
}
