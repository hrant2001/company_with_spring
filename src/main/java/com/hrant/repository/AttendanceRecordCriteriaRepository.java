package com.hrant.repository;

import com.hrant.model.AttendanceRecord;

import java.util.List;

public interface AttendanceRecordCriteriaRepository {
    List<AttendanceRecord> findByCriteria(boolean isEmployee, String searchingTerm, String date);
}
