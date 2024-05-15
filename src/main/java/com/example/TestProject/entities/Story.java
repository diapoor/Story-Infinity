package com.example.TestProject.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stories")
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "what is title")
    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description",nullable = true)
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "process",nullable = false,columnDefinition = "boolean default false")
    private boolean process;

    @Column(name = "status",nullable = false,columnDefinition = "boolean default false")
    private boolean status;

    @CreationTimestamp
    @Column(name = "datepost")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date datepost;

    //Relationship many-one user-story
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = true)
    private User user;

    //Relationship many-many author-story
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "story_author",
            joinColumns = @JoinColumn(name = "story_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors;

    //genres - story
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "story_genres",
            joinColumns = @JoinColumn(name = "story_id"),
            inverseJoinColumns = @JoinColumn(name = "genres_id"))
    private Set<Genres> genres;

}