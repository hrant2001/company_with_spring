package com.hrant.security.validation;

import com.hrant.dto.EmployeeDto;

public class EmployeeValidation {
    public static boolean isValid(EmployeeDto employeeDto) {
        return employeeDto != null
                && employeeDto.getFName() != null && !employeeDto.getFName().isEmpty()
                && employeeDto.getLName() != null && !employeeDto.getLName().isEmpty()
                && employeeDto.getEmail() != null && !employeeDto.getEmail().isEmpty()
                && employeeDto.getBirthday() != null
                && employeeDto.getDepartment() != null
                && employeeDto.getPosition() != null;
    }
}
