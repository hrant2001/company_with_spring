package com.hrant.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false, updatable = false)
    private Integer employeeId;

    @Column(name = "fname", nullable = false)
    private String fName;

    @Column(name = "lname", nullable = false)
    private String lName;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "email", nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    public Employee() {
    }

    public Employee(Integer employeeId) {
        this.employeeId = employeeId;
    }
    public Integer getEmployeeId() {
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartmentId(Department department) {
        this.department = department;
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
        return position.equals(employee.position) && department.equals(employee.department) && fName.equals(employee.fName) && lName.equals(employee.lName) && birthday.equals(employee.birthday) && email.equals(employee.email);
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


