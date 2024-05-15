package com.example.TestProject.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "chapters")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "content_chapter")
    @NotBlank(message = "content chapter not blank")
    private String content;

    @Column(name = "chapter_name")
    @NotBlank(message = "title not blank")
    private String title;

    @Column(name = "datepost")
    @CreationTimestamp
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date postDate;

    @ManyToOne
    @JoinColumn(name = "story_id")
    @JsonIgnore
    private Story story;

}
