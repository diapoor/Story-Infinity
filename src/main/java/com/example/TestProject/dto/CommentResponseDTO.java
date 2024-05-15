package com.example.TestProject.dto;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDTO {
    private  int id;
    private String nicknameComment;
    private String content;
    private Date date;

}
