package com.boardgame.boardgameservice.exceptions;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ErrorResponse {
    final private String error;
    final private String message;
    private Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
}
