package com.hrant.service;

import com.hrant.dto.DepartmentDto;
import com.hrant.dto.EmployeeDto;
import com.hrant.dto.PositionDto;
import com.hrant.model.Employee;
import com.hrant.repository.EmployeeRepository;
import com.hrant.util.DtoConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final static Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;
    private final PositionService positionService;
    private final DepartmentService departmentService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, PositionService positionService, DepartmentService departmentService) {
        this.employeeRepository = employeeRepository;
        this.positionService = positionService;
        this.departmentService = departmentService;
    }

    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        List<EmployeeDto> employeesDto = getEmployeesDto(employeeRepository.findAll());
        if (employeesDto.contains(employeeDto)) {
            LOGGER.info("The employee " + employeeDto + " is already in the list");
            System.out.println("The employee " + employeeDto + " is already in the list");
            return new EmployeeDto();
        }
        employeeDto.setPositionId(positionService.findPositionIdByName(employeeDto.getPositionName()));
        employeeDto.setDepartmentId(departmentService.findDepartmentIdByName(employeeDto.getDepartmentName()));
        return DtoConverter.employeeToDto(employeeRepository.save(DtoConverter.dtoToEmployee(employeeDto)));
    }

    public List<EmployeeDto> findAllEmployees() {
        return getEmployeesDto(employeeRepository.findAll());
    }

    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
//        List<EmployeeDto> employeesDto = getEmployeesDto(employeeRepository.findAll());
//        if (!employeesDto.contains(employeeDto)) {
//            LOGGER.info("The employee " + employeeDto + " is not in the list");
//            System.out.println("The employee " + employeeDto + " is not in the list");
//            return new EmployeeDto();
//        }
        int posId = positionService.findPositionIdByName(employeeDto.getPositionName());
        int depId = departmentService.findDepartmentIdByName(employeeDto.getDepartmentName());
        employeeDto.setPositionId(posId);
        employeeDto.setDepartmentId(depId);
        return DtoConverter.employeeToDto(employeeRepository.save(DtoConverter.dtoToEmployee(employeeDto)));
    }

    public void deleteEmployeeById(int id) {
        EmployeeDto employeeDto = findEmployeeById(id);
        if (employeeDto.getFName().isEmpty() && employeeDto.getLName().isEmpty()) {
            LOGGER.info("The employee with the id " + id + " is not in the list");
            System.out.println("The employee with the id " + id + " is not in the list");
            return;
        }
        employeeRepository.deleteById(id);
        LOGGER.info("The employee with the id " + id + " is successfully deleted from the list");
        System.out.println("The employee with the id " + id + " is successfully deleted from the list");
    }

    public EmployeeDto findEmployeeById(int id) {
        return DtoConverter.employeeToDto(employeeRepository.findById(id).orElse(DtoConverter.dtoToEmployee(new EmployeeDto())));
    }

    private List<EmployeeDto> getEmployeesDto(List<Employee> employees) {
        List<EmployeeDto> employeesDto = new ArrayList<>();
        for (Employee e : employees) {
            EmployeeDto employeeDto = DtoConverter.employeeToDto(e);
            PositionDto positionDto = positionService.findPositionById(e.getPositionId());
            DepartmentDto departmentDto = departmentService.findDepartmentById(e.getDepartmentId());
            employeeDto.setPositionName(positionDto.getName());
            employeeDto.setDepartmentName(departmentDto.getName());
            employeesDto.add(employeeDto);
        }
        return employeesDto;
    }
}
