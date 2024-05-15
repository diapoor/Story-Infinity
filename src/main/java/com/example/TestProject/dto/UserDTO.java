package com.example.TestProject.dto;

import com.example.TestProject.entities.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @Valid
    @NotNull(message = "Please input Your Information")
    private User user;
    private Integer employeeId;
    private List<String> roleName;
    private String confirmPassword;
}
