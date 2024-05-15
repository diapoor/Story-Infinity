package com.example.TestProject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class StoryRequestDTO {

    private int storyId;
    private String username;

    @NotBlank(message = "What is title?")
    private String title;



    @NotNull(message = "What is time?")
    @Past(message = "The posting date must be a date in the past")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date datePost;

    @NotNull(message = "finished or no?")
    private boolean process;

    @NotBlank(message = "What is story")
    private String description;

    private List<String> authorName;//save author

    @NotEmpty(message = "What is genres?")
    private List<String> genresName;//save Genres
}
