package com.example.TestProject.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userRating;

    @ManyToOne
    @JoinColumn(name = "story_id")
    private Story storyRating;

    @Column(name = "rating")
    private int rating;
}
