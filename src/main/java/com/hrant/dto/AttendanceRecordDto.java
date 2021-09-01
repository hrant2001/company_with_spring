package com.hrant.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class AttendanceRecordDto implements DtoMarker, Serializable {
    private Integer recordId;
    private LocalDateTime entranceTime;
    private LocalDateTime exitTime;
    private EmployeeDto employee;
    private String employeeFullName;

    public AttendanceRecordDto() {
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

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }

    public String getEmployeeFullName() {
        return employeeFullName;
    }

    public void setEmployeeFullName(String employeeFullName) {
        this.employeeFullName = employeeFullName;
    }

    @Override
    public String toString() {
        return recordId + " " + entranceTime + " " + exitTime + " " + employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttendanceRecordDto that = (AttendanceRecordDto) o;
        return recordId == that.recordId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordId);
    }
}
