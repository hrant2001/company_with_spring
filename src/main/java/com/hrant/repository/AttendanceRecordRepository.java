package com.hrant.repository;

import com.hrant.model.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Integer> {
//    @Query("from attendance_record where attendance_record.entranceTime <= :#{#record.getEntranceTime()} and attendance_record.exitTime >= :#{#record.getExitTime()} and concat(attendance_record.employee.department.name,attendance_record.employee.lName) like :#{#record.getEmployee().getFName()} and attendance_record.employee.department.name like '%:#{#record.getEmployee().getFName()}%'")
//    Optional<List<AttendanceRecord>> findAllByCriteria(AttendanceRecord record);
}
