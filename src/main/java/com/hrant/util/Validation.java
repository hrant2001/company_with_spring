package com.hrant.util;

import com.hrant.dto.EmployeeDto;

public class Validation {
    public static boolean isValid(EmployeeDto employeeDto) {
        return employeeDto != null && employeeDto.getFName() != null && employeeDto.getLName() != null && employeeDto.getEmail() != null
                && employeeDto.getBirthday() != null && employeeDto.getDepartment() != null && employeeDto.getPosition() != null;
    }
}
