package com.hrant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "employee")
public class Employee implements Serializable {

    @Id
    @Column(name = "employee_id", nullable = false, updatable = false)
    private int employeeId;

    @Column(name = "fname", nullable = false)
    private String fName;

    @Column(name = "lname", nullable = false)
    private String lName;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "position_id", nullable = false)
    private int positionId;

    @Column(name = "department_id", nullable = false)
    private int departmentId;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    public Employee() {
    }

    public Employee(String fName, String lName, LocalDate birthday) {
        this.fName = fName;
        this.lName = lName;
        this.birthday = birthday;
    }

    public Employee(String fName, String lName, LocalDate birthday, int positionId, int departmentId) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        Employee employee = (Employee) o;
        return positionId == employee.positionId && departmentId == employee.departmentId && fName.equals(employee.fName) && lName.equals(employee.lName) && birthday.equals(employee.birthday) && email.equals(employee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fName, lName, birthday, email, positionId, departmentId);
    }

    @Override
    public String toString() {
        return lName + " " + fName + " " + birthday;
    }
}


