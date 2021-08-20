package com.hrant.service;

import com.hrant.dto.AttendanceRecordDto;
import com.hrant.dto.EmployeeDto;
import com.hrant.model.AttendanceRecord;
import com.hrant.repository.AttendanceRecordRepository;
import com.hrant.util.DtoConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceRecordService {
    private final static Logger LOGGER = LoggerFactory.getLogger(AttendanceRecordService.class);

    private final AttendanceRecordRepository recordRepository;
    private final EmployeeService employeeService;

    @Autowired
    public AttendanceRecordService(AttendanceRecordRepository recordRepository, EmployeeService employeeService) {
        this.recordRepository = recordRepository;
        this.employeeService = employeeService;
    }

    public List<AttendanceRecordDto> findAllRecords() {
        return getRecordsDto(recordRepository.findAll());
    }

    private List<AttendanceRecordDto> getRecordsDto(List<AttendanceRecord> records) {
        List<AttendanceRecordDto> recordsDto = new ArrayList<>();
        for (AttendanceRecord r : records) {
            AttendanceRecordDto recordDto = DtoConverter.attendanceRecordToDto(r);
            EmployeeDto employeeDto = employeeService.findEmployeeById(r.getEmployeeId());
            recordDto.setEmployeeFullName(employeeDto.getFName() + " " + employeeDto.getLName());
            recordsDto.add(recordDto);
        }
        return recordsDto;
    }
}
