package com.example.TestProject.repositories;

import com.example.TestProject.entities.Employee;
import com.example.TestProject.entities.User;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    //get list employee by name
    @Query("select e from Employee e where e.name like %:name%")
    List<Employee> findEmployeeByName(@Param("name") String name);

    //get Username of employee
    @Query("SELECT  u.username FROM User u WHERE  u.employee.id = :employeeId")
    List<String> getALlUserByEmployee(@Param("employeeId") int employeeId);

    //delete User folow employee
    @Transactional
    @Modifying
    @Query("delete  from User u where  u.username = :username")
    void deleteUserByEmployee(@Param("username") String username);
}
