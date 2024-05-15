package com.example.TestProject.service;

import com.example.TestProject.dto.ChapterDTO;
import com.example.TestProject.entities.Chapter;
import com.example.TestProject.entities.Story;
import com.example.TestProject.repositories.ChapterRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ChapterService {
    private final ChapterRepository chapterRepository;
    //get chapter by story
    public List<Chapter> getChapterByStory(int storyId) {

        return chapterRepository.getChapterByStoryId(storyId);
    }

    //get chapter By id
    public Chapter getChapterById(int chapterId) {
        Optional<Chapter> chapterOptional = chapterRepository.findById(chapterId);
        if(chapterOptional.isPresent()) {
            Chapter chapter;
            chapter = chapterOptional.get();
            return chapter;
        } else {
            return null;
        }
    }

    //addChapter
    public void save(ChapterDTO chapterDTO) {
        Story story = chapterRepository.getStorysById(chapterDTO.getStoryId());
        if(story == null) throw new RuntimeException("Story not found.");//check story
        if(chapterDTO.getUsername() != null) { //from user
            if(story.getUser() == null || !story.getUser().getUsername().equals(chapterDTO.getUsername()))//check role
                throw new RuntimeException("You not are Author of this Story.");
        }
        Chapter chapter = chapterDTO.getChapter();
        chapter.setStory(story);
        chapterRepository.save(chapter);
    }

    //update chapter
    public void update(Chapter obj) {
        chapterRepository.save(obj);
    }

    //delete chapter
    public void deleteChapterById(int chapterId) {

        chapterRepository.deleteHistoryByChapter(chapterId);//delete hisory first
        chapterRepository.deleteById(chapterId); //delete chapter
    }

    //deleteByStory(when delete story or delete All chapter)
    public void deleteChapterByStory(int storyId) {
        List<Chapter> chapters = chapterRepository.getChapterByStoryId(storyId);
        chapters.forEach(chapter -> deleteChapterByStory(chapter.getId())); //delete history first
        chapterRepository.deleteChapterByStory(storyId); //delete chapter
    }
}
