package com.example.TestProject.repositories;

import com.example.TestProject.entities.Chapter;
import com.example.TestProject.entities.Story;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter,Integer> {
    @Query("SELECT c FROM Chapter c WHERE c.story.id = :storyId")
    List<Chapter> getChapterByStoryId(@Param("storyId")int storyId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Chapter c WHERE c.story.id =:storyId")
    void deleteChapterByStory(@Param("storyId") int storyId);

    @Modifying
    @Transactional
    @Query("DELETE FROM History h where h.chapterHistory.id = :chapterId")
    void deleteHistoryByChapter(@Param("chapterId") int chapterId);

    @Query("select s from Story s where  s.id = :sId")
    Story getStorysById(@Param("sId")int sId);
}
