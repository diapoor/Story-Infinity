package com.example.TestProject.repositories;

import com.example.TestProject.entities.Rating;
import com.example.TestProject.entities.Story;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Integer> {
    //get rating by story for admin
    @Query("SELECT r FROM Rating r where r.storyRating.id = :storyId")
    List<Rating> findRatingByStory(@Param("storyId") int storyId);

    //get rating point public
    @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.storyRating.id = :storyId")
    Double findAverageRatingByStoryId(@Param("storyId") int storyId);
    //delete rating by story
    @Modifying
    @Transactional
    @Query("DELETE FROM Rating r WHERE r.storyRating.id = :storyId")
    void deleteAllByStoryId(@Param("storyId") int storyId);

    @Query("SELECT s FROM Story s WHERE s.id = :id and s.status=true")
    Story findStoryById(@Param("id") int id);

}
