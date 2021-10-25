package com.hrant.service;

import com.hrant.dto.EmployeeDto;
import com.hrant.model.Employee;
import com.hrant.repository.EmployeeRepository;
import com.hrant.util.Validation;
import com.hrant.util.exception.employee.EmployeeAlreadyExistsException;
import com.hrant.util.exception.employee.EmployeeIdNotValidException;
import com.hrant.util.exception.employee.EmployeeNotFoundException;
import com.hrant.util.exception.employee.EmployeeNotValidException;
import com.hrant.util.mapper.EmployeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class EmployeeService {
    private final static Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Transactional
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        if (!Validation.isValid(employeeDto)) {
            LOGGER.error("The employee " + employeeDto + " is not a valid employee");
            throw new EmployeeNotValidException();
        }

        List<Employee> employees = employeeRepository.findAllByCriteria(new Employee(employeeDto.getFName(), employeeDto.getLName(), employeeDto.getBirthday())).orElse(null);
        if (employees == null || employees.isEmpty()) {
            return employeeMapper.toDto(employeeRepository.save(employeeMapper.toEntity(employeeDto)));
        }
        LOGGER.warn("The employee " + employeeDto + " exists");
        throw new EmployeeAlreadyExistsException();
    }

    public List<EmployeeDto> findAllEmployees() {
        return getEmployeesDto(employeeRepository.findAll());
    }

    public EmployeeDto findEmployeeById(Integer id) {
        if (id == null || id <= 0) {
            LOGGER.warn("Id " + id + " is not valid");
            throw new EmployeeIdNotValidException();
        }
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            LOGGER.warn("The employee with the id " + id + " was not found");
            throw new EmployeeNotFoundException();
        }
        return employeeMapper.toDto(employee);
    }

    @Transactional
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        if (!Validation.isValid(employeeDto) || employeeDto.getEmployeeId() == null || employeeDto.getEmployeeId() <= 0) {
            LOGGER.error("The employee " + employeeDto + " is not a valid employee");
            throw new EmployeeNotValidException();
        }

        Employee employee = employeeRepository.findById(employeeDto.getEmployeeId()).orElse(null);
        if (employee == null) {
            LOGGER.info("The employee " + employeeDto + " was not found");
            throw new EmployeeNotFoundException();
        } else {

            return employeeMapper.toDto(employeeRepository.save(employeeMapper.toEntity(employeeDto)));
        }
    }

    @Transactional
    public void deleteEmployeeById(Integer id) {
        if (id == null || id <= 0) {
            LOGGER.warn("Id " + id + " is not valid");
            throw new EmployeeIdNotValidException();
        }
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            LOGGER.warn("The employee with the id " + id + " was not found");
            throw new EmployeeNotFoundException();
        }
        employeeRepository.deleteById(id);
        LOGGER.info("The employee with the id " + id + " is successfully deleted from the list");
    }

    private List<EmployeeDto> getEmployeesDto(List<Employee> employees) {
        List<EmployeeDto> employeesDto = new ArrayList<>();
        for (Employee e : employees) {
            employeesDto.add(employeeMapper.toDto(e));
        }
        return employeesDto;
    }
}
