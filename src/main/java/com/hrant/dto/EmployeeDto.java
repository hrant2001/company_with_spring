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
    private PositionDto position;
    private DepartmentDto department;
    private boolean enabled = true;

    public EmployeeDto() {
    }

    public EmployeeDto(int employeeId) {
        this.employeeId = employeeId;
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

    public PositionDto getPosition() {
        return position;
    }

    public void setPosition(PositionDto position) {
        this.position = position;
    }

    public DepartmentDto getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDto department) {
        this.department = department;
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
        EmployeeDto that = (EmployeeDto) o;
        return position.equals(that.position) && department.equals(that.department) && fName.equals(that.fName) && lName.equals(that.lName) && birthday.equals(that.birthday) && email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fName, lName, birthday, email, position, department);
    }

    @Override
    public String toString() {
        return lName + " " + fName + " " + birthday;
    }
}
