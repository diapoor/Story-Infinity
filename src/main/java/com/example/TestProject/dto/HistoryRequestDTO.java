package com.example.TestProject.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HistoryRequestDTO {
    private int historyId;
    private int chapterId;
}
