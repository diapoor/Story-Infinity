package com.example.TestProject.repositories;

import com.example.TestProject.entities.Comment;
import com.example.TestProject.entities.Story;
import com.example.TestProject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    @Query("SELECT c FROM Comment c WHERE c.story.id = :storyId AND c.story.status = true ORDER BY  c.dateComment DESC ")
    List<Comment> findCommentByStory(@Param("storyId") int storyId);
    @Query("SELECT c FROM Comment c WHERE c.story.id = :storyId")
    List<Comment> findCommentByStoryAdmin(int storyId);

    //delete by story
    @Modifying
    @Transactional
    @Query("DELETE FROM Comment c WHERE c.story.id = :storyId")
    void deleteAllByStory(int storyId);

}
