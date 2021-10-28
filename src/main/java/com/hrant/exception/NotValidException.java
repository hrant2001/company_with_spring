package com.hrant.exception;

public class NotValidException extends RuntimeException {
    public NotValidException() {
        super();
    }

    public NotValidException(String message) {
        super(message);
    }

    public NotValidException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
