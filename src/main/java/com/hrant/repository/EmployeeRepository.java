package com.hrant.repository;

import com.hrant.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("from #{#entityName} where enabled=true")
    @Override
    List<Employee> findAll();

    @Query("from #{#entityName} where employeeId=:id and enabled=true")
    @Override
    Optional<Employee> findById(@Param("id") Integer id);

    @Query("from #{#entityName} where fname=:#{#employee.getFName()} and lname=:#{#employee.getLName()} and birthday=:#{#employee.getBirthday()} and enabled=true")
    Optional<List<Employee>> findAllByCriteria(Employee employee);

    @Modifying
    @Query("update #{#entityName} set enabled=false where employeeId=:id")
    @Override
    void deleteById(@Param("id") Integer id);
}
