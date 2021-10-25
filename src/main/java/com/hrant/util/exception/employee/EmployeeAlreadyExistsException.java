package com.hrant.util.exception.employee;

public class EmployeeAlreadyExistsException extends RuntimeException {
    public EmployeeAlreadyExistsException() {
        super();
    }

    public EmployeeAlreadyExistsException(String message) {
        super(message);
    }
}
