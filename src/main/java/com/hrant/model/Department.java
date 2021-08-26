package com.hrant.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "department")
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id", nullable = false, updatable = false)
    private int departmentId;

    @Column(name = "department_name", nullable = false)
    private String name;

    public Department() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return departmentId == that.departmentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId);
    }

    @Override
    public String toString() {
        return departmentId + " " + name;
    }
}

