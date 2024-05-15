package com.example.TestProject.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "histories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")

    private int id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User userHistory;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "chapter_id")
    private  Chapter chapterHistory;


    @CreationTimestamp
    @Column(name = "dateread")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date readedAt;

    public History(User userHistory, Chapter chapterHistory) {
        this.userHistory = userHistory;
        this.chapterHistory = chapterHistory;
    }
}
