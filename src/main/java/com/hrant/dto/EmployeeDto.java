package com.hrant.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class EmployeeDto implements Serializable {
    private int employeeId;
    private String fName;
    private String lName;
    private LocalDate birthday;
    private String email;
    private int positionId;
    private int departmentId;
    private String positionName;
    private String departmentName;
    private boolean enabled;

    public EmployeeDto() {
        fName = "";
        lName = "";
    }

    public EmployeeDto(String fName, String lName, LocalDate birthday) {
        this.fName = fName;
        this.lName = lName;
        this.birthday = birthday;
    }

    public EmployeeDto(String fName, String lName, LocalDate birthday, int positionId, int departmentId) {
        this.fName = fName;
        this.lName = lName;
        this.birthday = birthday;
        this.positionId = positionId;
        this.departmentId = departmentId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDto employeeDto = (EmployeeDto) o;
        return fName.equalsIgnoreCase(employeeDto.fName) && lName.equalsIgnoreCase(employeeDto.lName) && birthday.equals(employeeDto.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fName, lName, birthday);
    }

    @Override
    public String toString() {
        return lName + " " + fName + " " + birthday;
    }
}
