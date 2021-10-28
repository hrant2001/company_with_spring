package com.hrant.exception;

import java.time.LocalDateTime;

public class ExceptionResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public ExceptionResponse setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public ExceptionResponse setStatus(int status) {
        this.status = status;
        return this;
    }

    public ExceptionResponse setError(String error) {
        this.error = error;
        return this;
    }

    public ExceptionResponse setMessage(String message) {
        this.message = message;
        return this;
    }
}
