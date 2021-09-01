package com.hrant.util.mapper;

import com.hrant.dto.DepartmentDto;
import com.hrant.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper extends AbstractMapper<Department, DepartmentDto> {

    @Autowired
    DepartmentMapper() {
        super(Department.class, DepartmentDto.class);
    }
}
