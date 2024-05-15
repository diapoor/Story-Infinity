package com.example.TestProject.controller;

import com.example.TestProject.dto.EmployeeDTO;
import com.example.TestProject.entities.Employee;
import com.example.TestProject.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    //get list employee
    @GetMapping("/list")
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok().body(employeeService.getAll());
    }

    //search employee
    @GetMapping("/search")
    public ResponseEntity<List<Employee>> search(@RequestParam("name") String name) {
        return ResponseEntity.ok(employeeService.findEmployeeByName(name));
    }

    //details Employee
    @GetMapping("/details/{employeeId}")
    public ResponseEntity<?> detailsEmployee(@PathVariable("employeeId") int employeeId) {
        try {
            EmployeeDTO employeeDTO = employeeService.getOneById(employeeId);
            if (employeeDTO == null) {
                return ResponseEntity.badRequest().body("employee not foụnd");
            } else {
                return ResponseEntity.ok().body(employeeDTO);
            }
        } catch (NumberFormatException e) {//path variable invalid
            return ResponseEntity.badRequest().body("Employee not found.");
        }
    }

    //add employee
    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody @Valid Employee employee, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                // get messages error from BindingResult
                List<String> errorMessages = bindingResult.getAllErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList());
                // return messages error
                return ResponseEntity.badRequest().body(errorMessages);
            } else {
                employeeService.save(employee);
                return ResponseEntity.ok().body("add successfully");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("some problem.");
        }
    }

    //update employee
    @PutMapping("/update")
    public ResponseEntity<?> updateEmployee(@RequestBody @Valid Employee employee, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                // get messages error from BindingResult
                List<String> errorMessages = bindingResult.getAllErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList());
                // return messages error
                return ResponseEntity.badRequest().body(errorMessages);
            } else {
                if (employee.getId() > 0) {
                    employeeService.update(employee);
                    return ResponseEntity.ok("update employee successfully");
                }
                return ResponseEntity.notFound().build();
            }

        }catch (DataAccessException e){
            return ResponseEntity.badRequest().body("Can't update employee");
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("can't update employee: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("some problem.");
        }
    }

    //delete employee
    @DeleteMapping("/delete/{eId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("eId") int eId) {
        try {

                if (eId > 0) {
                    employeeService.deleteById(eId);
                    return ResponseEntity.ok("delete employee successfully");
                }
                return ResponseEntity.notFound().build();

        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().body("couldn't delete employee");
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("couldn't delete employee: " + e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body("some problem.");
        }
    }
}
