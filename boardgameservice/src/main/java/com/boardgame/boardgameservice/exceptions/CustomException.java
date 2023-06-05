package com.boardgame.boardgameservice.exceptions;

public class CustomException {

    public static class ValidationException extends RuntimeException {
        public ValidationException(String message) {
            super(message);
        }
    }
}
