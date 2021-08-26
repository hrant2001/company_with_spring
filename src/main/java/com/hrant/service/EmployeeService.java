package com.hrant.service;

import com.hrant.dto.EmployeeDto;
import com.hrant.model.Employee;
import com.hrant.repository.EmployeeRepository;
import com.hrant.util.DtoConverter;
import com.hrant.util.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeService {
    private final static Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;


//    public EmployeeService(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }

    public EmployeeDto addEmployee(EmployeeDto employeeDto) throws IllegalArgumentException {
        if (Validation.isNotValidEmployeeDto(employeeDto)) {
            LOGGER.info("The employee " + employeeDto + " is not a valid employee");
            throw new IllegalArgumentException();
        }

        List<EmployeeDto> employeesDto = getEmployeesDto(employeeRepository.findAll());
        if (employeesDto.contains(employeeDto)) {
            LOGGER.info("The employee " + employeeDto + " is already in the list");
            throw new IllegalArgumentException();
        }
        return DtoConverter.employeeToDto(employeeRepository.save(DtoConverter.dtoToEmployee(employeeDto)));
    }

    public List<EmployeeDto> findAllEmployees() {
        return getEmployeesDto(employeeRepository.findAll());
    }

    public EmployeeDto updateEmployee(EmployeeDto employeeDto) throws IllegalArgumentException, NoSuchElementException {
        if (Validation.isNotValclearidEmployeeDto(employeeDto)) {
            LOGGER.info("The employee " + employeeDto + " is not a valid employee");
            throw new IllegalArgumentException();
        }

        Employee employee = employeeRepository.findById(employeeDto.getEmployeeId()).orElse(null);
        if (employee == null) {
            LOGGER.info("The employee " + employeeDto + " is not in the list");
            throw new NoSuchElementException();
        } else {
            return DtoConverter.employeeToDto(employeeRepository.save(DtoConverter.dtoToEmployee(employeeDto)));
        }
    }

    public void deleteEmployeeById(int id) throws NoSuchElementException {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            LOGGER.info("The employee with the id " + id + " is not in the list");
            throw new NoSuchElementException();
        }
        employeeRepository.deleteById(id);
        LOGGER.info("The employee with the id " + id + " is successfully deleted from the list");
    }

    public EmployeeDto findEmployeeById(int id) throws NoSuchElementException {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            LOGGER.info("The employee with the id " + id + " is not in the list");
            throw new NoSuchElementException();
        }
        return DtoConverter.employeeToDto(employee);
    }

    private List<EmployeeDto> getEmployeesDto(List<Employee> employees) {
        List<EmployeeDto> employeesDto = new ArrayList<>();
        for (Employee e : employees) {
            employeesDto.add(DtoConverter.employeeToDto(e));
        }
        return employeesDto;
    }
}
