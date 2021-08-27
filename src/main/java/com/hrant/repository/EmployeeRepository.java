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

    @Modifying
    //@Query(value = "update employee set enabled=false where employee_id=:id", nativeQuery = true) //change to hql
    @Query("update employee set enabled=false where employeeId=:id")
    @Override
    void deleteById(@Param("id") Integer id);

    //@Query(value = "select * from employee where enabled=true", nativeQuery = true)
    @Query(value = "from employee where enabled=true")
    @Override
    List<Employee> findAll();

    //@Query(value = "select * from employee where employee_id=:id and enabled=true", nativeQuery = true)
    @Query(value = "from employee where employeeId=:id and enabled=true")
    @Override
    Optional<Employee> findById(@Param("id") Integer id);
}
