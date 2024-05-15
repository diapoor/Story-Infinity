package com.example.TestProject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "favorites")
public class Favorite {
    @EmbeddedId
    private FavoriteId id;
    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @MapsId("storyId")
    @ManyToOne()
    @JoinColumn(name = "story_id")
    @JsonIgnore
    private Story story;

    public Favorite(User user,Story story) {
        this.user = user;
        this.story = story;
    }
}
