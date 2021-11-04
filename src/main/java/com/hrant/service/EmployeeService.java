package com.hrant.service;

import com.hrant.dto.EmployeeDto;
import com.hrant.model.Employee;
import com.hrant.repository.EmployeeRepository;
import com.hrant.security.validation.EmployeeValidation;
import com.hrant.exception.AlreadyExistsException;
import com.hrant.exception.NotValidException;
import com.hrant.exception.NotFoundException;
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
        if (!EmployeeValidation.isValid(employeeDto)) {
            LOGGER.error("The employee {} is not a valid employee", employeeDto);
            throw new NotValidException("The employee " + employeeDto + " is not a valid employee");
        }

        List<Employee> employees = employeeRepository.findAllByCriteria(new Employee(employeeDto.getFName(), employeeDto.getLName(), employeeDto.getBirthday())).orElse(null);
        if (employees == null || employees.isEmpty()) {
            return employeeMapper.toDto(employeeRepository.save(employeeMapper.toEntity(employeeDto)));
        }
        LOGGER.warn("The employee {} exists", employeeDto);
        throw new AlreadyExistsException("The employee " + employeeDto + " exists");
    }

    public List<EmployeeDto> findAllEmployees() {
        return getEmployeesDto(employeeRepository.findAll());
    }

    public EmployeeDto findEmployeeById(Integer id) {
        if (id == null || id <= 0) {
            LOGGER.warn("Id {} is not valid", id);
            throw new NotValidException("Id " + id + " is not valid");
        }
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            LOGGER.warn("The employee with the id {} was not found", id);
            throw new NotFoundException("The employee with the id " + id + " was not found");
        }
        return employeeMapper.toDto(employee);
    }

    @Transactional
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        if (!EmployeeValidation.isValid(employeeDto) || employeeDto.getEmployeeId() == null || employeeDto.getEmployeeId() <= 0) {
            LOGGER.error("The employee {} is not a valid employee", employeeDto);
            throw new NotValidException("The employee " + employeeDto + " is not a valid employee");
        }

        Employee employee = employeeRepository.findById(employeeDto.getEmployeeId()).orElse(null);
        if (employee == null) {
            LOGGER.info("The employee {} was not found", employeeDto);
            throw new NotFoundException("The employee " + employeeDto + " was not found");
        }

        List<Employee> employees = employeeRepository.findAllByCriteria(new Employee(employeeDto.getFName(), employeeDto.getLName(), employeeDto.getBirthday())).orElse(null);
        if (employees != null && !employees.isEmpty()) {
            LOGGER.warn("The employee {} exists", employeeDto);
            throw new AlreadyExistsException("The employee " + employeeDto + " exists");
        }
        return employeeMapper.toDto(employeeRepository.save(employeeMapper.toEntity(employeeDto)));
    }

    @Transactional
    public void deleteEmployeeById(Integer id) {
        if (id == null || id <= 0) {
            LOGGER.warn("Id {} is not valid", id);
            throw new NotValidException("Id " + id + " is not valid");
        }
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            LOGGER.warn("The employee with the id {} was not found", id);
            throw new NotFoundException("The employee with the id " + id + " was not found");
        }
        employeeRepository.deleteById(id);
        LOGGER.info("The employee with the id {} is successfully deleted from the list", id);
    }

    private List<EmployeeDto> getEmployeesDto(List<Employee> employees) {
        List<EmployeeDto> employeesDto = new ArrayList<>();
        for (Employee e : employees) {
            employeesDto.add(employeeMapper.toDto(e));
        }
        return employeesDto;
    }
}
