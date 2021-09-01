package com.hrant.util.mapper;

import com.hrant.dto.AttendanceRecordDto;
import com.hrant.model.AttendanceRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttendanceRecordMapper extends AbstractMapper<AttendanceRecord, AttendanceRecordDto> {

    @Autowired
    AttendanceRecordMapper() {
        super(AttendanceRecord.class, AttendanceRecordDto.class);
    }
}
