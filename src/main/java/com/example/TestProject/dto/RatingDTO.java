package com.example.TestProject.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RatingDTO {
    @NotNull(message = "Please input storyId")
    @Positive(message = "storyId must be is positive number")
    private int storyId;
    @NotNull(message = "story not found")
    @Positive(message = "rating point must be is positive number")
    @Max(value = 5,message = "rating must 1-5")
    private int rating;
}
