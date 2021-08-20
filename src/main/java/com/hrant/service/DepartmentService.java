package com.hrant.service;

import com.hrant.dto.DepartmentDto;
import com.hrant.model.Department;
import com.hrant.repository.DepartmentRepository;
import com.hrant.util.DtoConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DepartmentService {
    private final static Logger LOGGER = LoggerFactory.getLogger(DepartmentService.class);

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public DepartmentDto findDepartmentById(int id) {
        return DtoConverter.departmentToDto(((departmentRepository.findById(id).orElse((new Department())))));
    }

    public List<DepartmentDto> findAllDepartments() {
        List<DepartmentDto> departmentsDto = new ArrayList<>();
        List<Department> departments = departmentRepository.findAll();

        for (Department d : departments) {
            DepartmentDto departmentDto = DtoConverter.departmentToDto((d));
            departmentsDto.add(departmentDto);
        }

        return departmentsDto;
    }

    public int findDepartmentIdByName(String name) {
        DepartmentDto departmentDto = findAllDepartments().stream().filter(p -> p.getName().equalsIgnoreCase(name)).findAny().orElse(null);
        return Objects.nonNull(departmentDto) ? departmentDto.getDepartmentId() : -1;
    }
}
