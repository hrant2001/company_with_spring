package com.hrant.service;

import com.hrant.dto.AttendanceRecordDto;
import com.hrant.model.AttendanceRecord;
import com.hrant.repository.AttendanceRecordRepository;
import com.hrant.util.mapper.AttendanceRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceRecordService {
    private final static Logger LOGGER = LoggerFactory.getLogger(AttendanceRecordService.class);

    private final AttendanceRecordRepository recordRepository;

    private final AttendanceRecordMapper recordMapper;

    @Autowired
    public AttendanceRecordService(AttendanceRecordRepository recordRepository, AttendanceRecordMapper recordMapper) {
        this.recordRepository = recordRepository;
        this.recordMapper = recordMapper;
    }

    public List<AttendanceRecordDto> findAllRecords() {
        return getRecordsDto(recordRepository.findAll());
    }

    public List<AttendanceRecordDto> findRecordsByCriteria(boolean isEmployee, String criteria, String date) {
        if (criteria == null)
            criteria = "";
        if (date == null)
            date = "";
        return getRecordsDto(recordRepository.findByCriteria(isEmployee, criteria, date));
    }

    private List<AttendanceRecordDto> getRecordsDto(List<AttendanceRecord> records) {
        List<AttendanceRecordDto> recordsDto = new ArrayList<>();
        for (AttendanceRecord r : records) {
            AttendanceRecordDto recordDto = recordMapper.toDto(r);
            recordDto.setEmployeeFullName(r.getEmployee().getFName() + " " + r.getEmployee().getLName());
            recordsDto.add(recordDto);
        }
        return recordsDto;
    }
}
