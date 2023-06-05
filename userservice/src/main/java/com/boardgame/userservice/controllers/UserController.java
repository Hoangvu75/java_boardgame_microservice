package com.boardgame.userservice.controllers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardgame.userservice.requests.CreateUserRequest;
import com.boardgame.userservice.services.UserService;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(
            @Valid @RequestBody CreateUserRequest request,
            BindingResult bindingResult) {

        checkForValidation(bindingResult);

        return ResponseEntity.ok(userService.create(request));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> find(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(userService.find(id));
    }

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(userService.getAll());
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
