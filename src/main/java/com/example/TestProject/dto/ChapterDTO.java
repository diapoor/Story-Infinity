package com.example.TestProject.dto;

import com.example.TestProject.entities.Chapter;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.util.Date;

@Data
public class ChapterDTO {
    @Min(value = 1,message = "story not found")
    private int storyId;
    @Valid
    @NotNull(message = "Please enter data for the chapter ")
    private Chapter chapter;
    private String username;
}
