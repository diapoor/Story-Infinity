package com.example.TestProject.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "what your name?")
    @Size(min = 5,max = 250,message = "Name must be 3 - 250 characters long and only letters are accepted")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "what is gender?")
    @Size(min = 3,max = 10,message = "gender is limited to 3 - 10 characters")
    @Column(name = "gender")
    private String gender;

    @NotBlank(message = "where is your live?")
    @Size(min = 2,max = 50,message = "Maximum address 240 characters")
    @Column(name = "address")
    private String address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return getId() == employee.getId() && Objects.equals(getName(), employee.getName()) && Objects.equals(getGender(), employee.getGender()) && Objects.equals(getAddress(), employee.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getGender(), getAddress());
    }
}
