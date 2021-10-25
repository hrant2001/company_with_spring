package com.hrant.service;

import com.hrant.dto.DepartmentDto;
import com.hrant.model.Department;
import com.hrant.repository.DepartmentRepository;
import com.hrant.util.mapper.DepartmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {
    private final static Logger LOGGER = LoggerFactory.getLogger(DepartmentService.class);

    private final DepartmentRepository departmentRepository;

    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    public List<DepartmentDto> findAllDepartments() {
        List<DepartmentDto> departmentsDto = new ArrayList<>();
        List<Department> departments = departmentRepository.findAll();

        for (Department d : departments) {
            DepartmentDto departmentDto = departmentMapper.toDto((d));
            departmentsDto.add(departmentDto);
        }

        return departmentsDto;
    }
}
