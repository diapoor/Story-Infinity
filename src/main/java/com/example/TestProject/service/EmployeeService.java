package com.example.TestProject.service;

import com.example.TestProject.dto.EmployeeDTO;
import com.example.TestProject.entities.Employee;
import com.example.TestProject.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    //get all
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    //get by name
    public List<Employee> findEmployeeByName(String name) {
        return employeeRepository.findEmployeeByName(name);
    }

    //check employee
    public Employee checkEmployee(int eId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(eId);
        if (optionalEmployee.isPresent()) {
            Employee e;
            e = optionalEmployee.get();
            return e;
        }
        return null;
    }

    public EmployeeDTO getOneById(int employeeId) {
        Employee employee = checkEmployee(employeeId);
        if (employee != null) {
            List<String> users = employeeRepository.getALlUserByEmployee(employeeId);
            return new EmployeeDTO(employee, users);
        }
        return null;
    }


    public void update(Employee obj) {
        Employee employeeCheck = checkEmployee(obj.getId());
        if (employeeCheck == null) throw new RuntimeException("Error: Employee not found");
        employeeRepository.save(obj);
    }

    public Employee save(Employee obj) {
        return employeeRepository.save(obj);
    }


    public void deleteById(int id) {
        EmployeeDTO employeeDTO = getOneById(id);
        if(employeeDTO == null) throw new RuntimeException("Employee not found");
        if(!employeeDTO.getUsername().isEmpty()) throw new RuntimeException("please delete user first"); //coldn't delete if have username
        employeeRepository.deleteById(employeeDTO.getEmployee().getId());
    }
}
