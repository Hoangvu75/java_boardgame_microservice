package com.boardgame.boardgameservice.controllers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardgame.boardgameservice.requests.CreateBoardgameRequest;
import com.boardgame.boardgameservice.services.BoardgameService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/boardgame")
@RequiredArgsConstructor
public class BoardgameController {
    @Autowired
    private final BoardgameService boardgameService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(
            @Valid @RequestBody CreateBoardgameRequest request,
            BindingResult bindingResult) {

        checkForValidation(bindingResult);

        return ResponseEntity.ok(boardgameService.create(request));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> find(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(boardgameService.find(id));
    }

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(boardgameService.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(boardgameService.delete(id));
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
