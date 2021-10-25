package com.hrant.util.exception.employee;

public class EmployeeNotValidException extends RuntimeException {
    public EmployeeNotValidException() {
        super();
    }

    public EmployeeNotValidException(String message) {
        super(message);
    }
}

