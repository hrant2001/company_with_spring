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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public List<AttendanceRecordDto> findAllRecordsByCriteria(String from, String to, String empOrDep, boolean isEmp) {
         return getRecordsDto(recordRepository.findAll());
//        AttendanceRecord record = new AttendanceRecord();
//        if (from.equals("null") || from.isEmpty()) {
//            record.setEntranceTime(LocalDateTime.MIN);
//        } else {
//            record.setEntranceTime(LocalDate.parse(from).atStartOfDay());
//        }
//
//        if (to.equals("null") || to.isEmpty()) {
//            record.setExitTime(LocalDateTime.MAX);
//        } else {
//            record.setEntranceTime(LocalDate.parse(to).atStartOfDay());
//        }
//
//        if (isEmp) {
//            record.getEmployee().setFName(empOrDep);
//            record.getEmployee().getDepartment().setName(""); //maybe error
//        } else {
//            record.getEmployee().setFName("");
//            record.getEmployee().getDepartment().setName(empOrDep); //maybe error
//        }
//        return getRecordsDto(Objects.requireNonNull(recordRepository.findAllByCriteria(record).orElse(null))); //change
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
