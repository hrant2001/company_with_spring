package com.hrant.repository;

import com.hrant.model.AttendanceRecord;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRecordCriteriaRepository {
    List<AttendanceRecord> findByCriteria(boolean isEmployee, String searchingTerm, String date);
}
