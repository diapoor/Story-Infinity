package com.example.TestProject.repositories;

import com.example.TestProject.entities.Favorite;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite,Integer> {
    @Query("SELECT f FROM Favorite f WHERE f.user.id = :userId and f.story.status = true ")
    List<Favorite> getListFavovoriteByUser(@Param("userId") int userid);

    //check the existence of the favorite
    @Query("SELECT f FROM Favorite f WHERE f.user.id = :userId and f.story.id = :storyId")
    Optional<Favorite> findFavoriteByUserAndStory(@Param("userId") int userId,@Param("storyId") int storyId);

    //delete favorite
    @Modifying
    @Transactional
    @Query("DELETE FROM Favorite f WHERE f.user.id = :userId and f.story.id = :storyId")
    void deleteFavorite(@Param("userId") int userId,@Param("storyId") int storyId);

    //delete favorite by user
    @Modifying
    @Transactional
    @Query("DELETE FROM Favorite f WHERE f.user.id = :userId")
    void deleteAllByUser(@Param("userId") int userId);

    //delete favorite by story
    @Modifying
    @Transactional
    @Query("DELETE FROM Favorite f WHERE f.story.id= :storyId and f.user.id = :userId")
    void deleteOne(@Param("storyId") int storyId,@Param("userId")int userId);

}
