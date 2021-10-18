package com.hrant.repository;

import com.hrant.model.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Integer>,
        AttendanceRecordCriteriaRepository, JpaSpecificationExecutor<AttendanceRecord> {

}
