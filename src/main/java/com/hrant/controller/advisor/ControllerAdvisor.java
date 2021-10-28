package com.hrant.controller.advisor;

import com.hrant.exception.ExceptionResponse;
import com.hrant.exception.AlreadyExistsException;
import com.hrant.exception.NotValidException;
import com.hrant.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Object> handleEmployeeAlreadyException(AlreadyExistsException ex, WebRequest request) {

        ExceptionResponse body = new ExceptionResponse();
        body.setTimestamp(LocalDateTime.now())
                .setStatus(HttpStatus.BAD_REQUEST.value())
                .setError(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .setMessage(ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleEmployeeNotFoundException(NotFoundException ex, WebRequest request) {

        ExceptionResponse body = new ExceptionResponse();
        body.setTimestamp(LocalDateTime.now())
                .setStatus(HttpStatus.NOT_FOUND.value())
                .setError(HttpStatus.NOT_FOUND.getReasonPhrase())
                .setMessage(ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotValidException.class)
    public ResponseEntity<Object> handleEmployeeNotValidException(NotValidException ex, WebRequest request) {

        ExceptionResponse body = new ExceptionResponse();
        body.setTimestamp(LocalDateTime.now())
                .setStatus(HttpStatus.BAD_REQUEST.value())
                .setError(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .setMessage(ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
