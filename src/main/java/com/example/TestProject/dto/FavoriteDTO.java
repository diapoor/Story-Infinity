package com.example.TestProject.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FavoriteDTO {
    private String storyName;
    @NotNull(message = "story not found")
    @Min(value = 1,message = "story not found")
    private int storyId;
}
