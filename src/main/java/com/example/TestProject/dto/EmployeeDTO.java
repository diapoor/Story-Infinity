package com.example.TestProject.dto;

import com.example.TestProject.entities.Employee;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeDTO {
    private Employee employee;
    private List<String> username;
}
