package com.example.TestProject.dto;

import com.example.TestProject.entities.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CommentRequestDTO {
    @NotBlank(message = "what is comment")
    private String content;

    @NotNull (message = "story not found")
    @Positive(message = "story not found")
    private int storyId;




}
