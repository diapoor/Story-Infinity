package com.example.TestProject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class HistoryResponseDTO {
    private String storyName;
    private String ChapterName;
    private int storyId;
    private int historyId;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date readAt;
}
