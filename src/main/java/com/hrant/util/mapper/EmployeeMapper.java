package com.hrant.util.mapper;

import com.hrant.dto.EmployeeDto;
import com.hrant.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper extends AbstractMapper<Employee, EmployeeDto> {

    @Autowired
    EmployeeMapper() {
        super(Employee.class, EmployeeDto.class);
    }
}
