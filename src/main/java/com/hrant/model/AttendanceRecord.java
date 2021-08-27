package com.hrant.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "attendance_record")
public class AttendanceRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id", nullable = false, updatable = false)
    private Integer recordId;

    @Column(name = "entrance_time", nullable = false)
    private LocalDateTime entranceTime;

    @Column(name = "exit_time", nullable = false)
    private LocalDateTime exitTime;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public AttendanceRecord() {
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public LocalDateTime getEntranceTime() {
        return entranceTime;
    }

    public void setEntranceTime(LocalDateTime entranceTime) {
        this.entranceTime = entranceTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return recordId + " " + entranceTime + " " + exitTime + " " + employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttendanceRecord that = (AttendanceRecord) o;
        return recordId == that.recordId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordId);
    }
}
