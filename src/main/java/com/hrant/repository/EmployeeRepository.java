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
    @Query(value = "update employee set enabled=false where employee_id=:id", nativeQuery = true) //change to hql
//    @Query("update Employee e set r=e.enabled=0 where e.employeeId=:id")
    @Override
    void deleteById(@Param("id") Integer id);

    @Query(value = "select * from employee where enabled=true", nativeQuery = true)
    @Override
    List<Employee> findAll();

    @Query(value = "select * from employee where employee_id=:id and enabled=true", nativeQuery = true)
    @Override
    Optional<Employee> findById(@Param("id") Integer id);
}
