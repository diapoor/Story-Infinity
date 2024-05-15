package com.example.TestProject.repositories;

import com.example.TestProject.entities.*;
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
public interface StoryRepository extends JpaRepository<Story,Integer> {
    //Jpql for search story
    @Query("SELECT s FROM Story s" +
            " LEFT JOIN s.authors a" +
            " LEFT JOIN s.user u" +
            " LEFT JOIN s.genres g" +
            " WHERE (s.title LIKE %:keyword%" +
            " OR g.name LIKE %:keyword%" +
            " OR u.nickname LIKE %:keyword%" +
            " OR a.name LIKE %:keyword%) AND s.status = true")
    List<Story> getListStoryByKeyword(@Param("keyword") String keyword);

    //get story(public)
    @Query("SELECT s FROM Story s WHERE s.status=true ")
    List<Story> getStoriesPublic();

    //get story by  id
    @Query("SELECT s FROM Story s WHERE s.id = :id and s.status=true")
    Optional<Story> findById(@Param("id") int id);

    //get list story by author
    @Query("SELECT s FROM Story s LEFT JOIN s.authors a WHERE a.id = :authorId")
    List<Story> getStoriesByAuthorId(@Param("authorId") int authorId);


    //get list story by user
    @Query("SELECT s FROM Story s LEFT JOIN s.user u WHERE u.id = :userId")
    List<Story> getStoriesByUserId(@Param("userId") int userId);

    @Query("select s from Story s where  s.id = :sId")
    Story getStorysById(@Param("sId")int sId);

    //get userbyusername
    @Query("SELECT u FROM  User  u WHERE u.username like :username")
    User findUserByUsername(@Param("username") String username);
    //get genre by name
    @Query("SELECT g FROM Genres g WHERE g.name = :name")
    Genres findGenresByName(String name);
    //get author by name
    @Query("SELECT a FROM Author a WHERE a.name = :name")
    Author findAuthorByName(String name);

    //delete rating folow story
    @Modifying
    @Transactional
    @Query("DELETE FROM Rating r WHERE r.storyRating.id = :storyId")
    void deleteRatingByStoryId(@Param("storyId") int storyId);


    //delete favorite by story
    @Modifying
    @Transactional
    @Query("DELETE FROM Favorite f WHERE f.story.id= :storyId")
    void deleteFavoriteByStory(@Param("storyId") int storyId);

    //delete chapterByStory
    @Modifying
    @Transactional
    @Query("DELETE FROM Chapter c WHERE c.story.id =:storyId")
    void deleteChapterByStory(@Param("storyId") int storyId);

    //delete comment by story
    @Modifying
    @Transactional
    @Query("DELETE FROM Comment c WHERE c.story.id = :storyId")
    void deleteCommentByStory(@Param("storyId")int storyId);

    @Modifying
    @Transactional
    @Query("DELETE FROM History h where h.chapterHistory.id = :chapterId")
    void deleteHistoryByChapter(@Param("chapterId") int chapterId);
}
