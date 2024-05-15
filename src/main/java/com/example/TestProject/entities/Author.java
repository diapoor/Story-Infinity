package com.example.TestProject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "What is name Author?")
    @Column(name = "name",nullable = false)
    private String name;

    @Email(message = "email must be email format")
    @Column(name = "email",nullable = true)
    private String email;

    @NotBlank(message = "Please input information of author")
    @Column(name = "information",nullable = false)
    private String information;

}
