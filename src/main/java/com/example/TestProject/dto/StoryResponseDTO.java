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
public class StoryResponseDTO {
    private int storyId;
    private String username;

    private String title;


    private Date datePost;

    private boolean process;


    private String description;

    private List<String> authorName;//save author

    private List<String> genresName;//save Genres
}
