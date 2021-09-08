package com.hrant.controller;

import com.hrant.dto.AttendanceRecordDto;
import com.hrant.service.AttendanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/records")
public class AttendanceRecordController {

    private final AttendanceRecordService recordService;

    @Autowired
    public AttendanceRecordController(AttendanceRecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping
    public ResponseEntity<List<AttendanceRecordDto>> getAllRecords() {
        List<AttendanceRecordDto> recordsDto = recordService.findAllRecords();
        return new ResponseEntity<>(recordsDto, HttpStatus.OK);
    }

    @GetMapping("/by_criteria/{from}/{to}/{empOrDep}/{isEmp}")
    public ResponseEntity<List<AttendanceRecordDto>> getAllRecordsByCriteria(@PathVariable String from, @PathVariable String to, @PathVariable String empOrDep, @PathVariable boolean isEmp) {
        List<AttendanceRecordDto> recordsDto = recordService.findAllRecordsByCriteria(from, to, empOrDep, isEmp);
        return new ResponseEntity<>(recordsDto, HttpStatus.OK);
    }
}
