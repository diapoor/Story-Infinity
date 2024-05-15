package com.example.TestProject.controller;

import com.example.TestProject.dto.ChapterDTO;
import com.example.TestProject.entities.Chapter;
import com.example.TestProject.entities.Story;
import com.example.TestProject.repositories.ChapterRepository;
import com.example.TestProject.service.ChapterService;
import com.example.TestProject.service.JwtService;
import com.example.TestProject.service.StoryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/chapter")
@Slf4j
@RequiredArgsConstructor
public class ChapterController {
    private final ChapterRepository chapterRepository;

    private final ChapterService chapterService;
    private final StoryService storyService;

    //get list chapter by story (public)

    @GetMapping("/public/{storyId}/list")
    public ResponseEntity<?> getListChapterByStory(@PathVariable("storyId") int storyId) {
        try {
            if(storyService.findStoryById(storyId)==null)
                return ResponseEntity.status(404).body("Story not found");
            List<Chapter> chapters = chapterService.getChapterByStory(storyId);
            if(chapters.isEmpty()) return ResponseEntity.status(404).body("Story not found");
            return ResponseEntity.ok(chapters);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
    //get one chapter(public)
    @GetMapping("/public/{storyId}/{chapterId}")
    public ResponseEntity<?> getOneChapter(@PathVariable("storyId") int storyId,@PathVariable("chapterId") int chapterId) {
        try {
            if(storyService.findStoryById(storyId)==null)
                return ResponseEntity.status(404).body("Story not found");
            Chapter chapter = chapterService.getChapterById(chapterId);
            if (chapter == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok().body(chapter);
            }
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    //create Chapter(user)
    @PostMapping("/user/creatChapter")
    public ResponseEntity<?> createChapterUser(@RequestBody @Valid ChapterDTO chapterDTO, BindingResult result, HttpServletRequest request) {
        try {
            if (result.hasErrors()) {
                // get messages error from BindingResult
                List<String> errorMessages = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
                // return messages error
                return ResponseEntity.badRequest().body(errorMessages);
            }

            String token = JwtService.extractToken(request);
            String username = JwtService.extractInfoFromToken(token);
            chapterDTO.setUsername(username);

            chapterService.save(chapterDTO);

            return ResponseEntity.ok().body("Created successfully");
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Can't create Chapter: "+e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    //create Chapter(admin)
    @PostMapping("/admin/createChapter")
    public ResponseEntity<?> createChapterAdmin(@RequestBody @Valid ChapterDTO chapterDTO, BindingResult result) {
        try {
            if (result.hasErrors()) {
                // get messages error from BindingResult
                List<String> errorMessages = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
                // return messages error
                return ResponseEntity.badRequest().body(errorMessages);
            }
            chapterDTO.setUsername(null);
            chapterService.save(chapterDTO);
            return ResponseEntity.ok().body("Created successfully");
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Can't create Chapter: "+e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    //delete one(user)
    @DeleteMapping("/user/{storyId}/{chapterId}")
    public ResponseEntity<?> deleteChapter(@PathVariable("storyId") int storyId,@PathVariable("chapterId") int chapterId,HttpServletRequest request) {
        try {

            String token = JwtService.extractToken(request);
            String username = JwtService.extractInfoFromToken(token);
            Chapter chapter = chapterService.getChapterById(chapterId);

            if(chapter == null) return ResponseEntity.status(404).body("Chapter Not found"); // check chapter

            if(chapter.getStory().getId() != storyId)
                return ResponseEntity.badRequest().body("Can't delete chapter: chapters and stories are not in the same system");//chek story

            if(chapter.getStory().getUser() == null || !chapter.getStory().getUser().getUsername().equals(username))//check role
                return ResponseEntity.status(403).body("You are not Author of this story");
            chapterService.deleteChapterById(chapterId);
            return ResponseEntity.ok().body("Deleted");
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("Couldn't delete Chapter: chapterId incorrect");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("couldn't delete chapter: " + e.getMessage());
        }
    }
    //delete one(admin)
    @DeleteMapping("/admin/{storyId}/{chapterId}")
    public ResponseEntity<?> deleteChapter(@PathVariable("storyId") int storyId,@PathVariable("chapterId") int chapterId) {
        try {

            Chapter chapter = chapterService.getChapterById(chapterId);

            if(chapter == null) return ResponseEntity.status(404).body("Chapter Not found"); // check chapter

            if(chapter.getStory().getId() != storyId)
                return ResponseEntity.badRequest().body("Can't delete chapter: chapters and stories are not in the same system");//chek story

            chapterService.deleteChapterById(chapterId);
            return ResponseEntity.ok().body("Deleted");
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("Couldn't delete Chapter: chapterId incorrect");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("couldn't delete chapter: " + e.getMessage());
        }
    }

    //delete chapter by story
    @DeleteMapping("/user/deleteByStory")
    public ResponseEntity<?> deleteChapterByStory(@RequestBody @Valid ChapterDTO chapterDTO) {
        try {
            //delete chapter by story
            chapterService.deleteChapterByStory(chapterDTO.getStoryId());
            return ResponseEntity.ok().body("deleted.");
        } catch (DataAccessException dataAccessException) {
            return ResponseEntity.badRequest().body("delete failed.");
        }
    }


    //update Chapter
    @PutMapping("/user/updateChapter")
    public ResponseEntity<?> updateChapter(@RequestBody @Valid ChapterDTO chapterDTO,HttpServletRequest request,BindingResult result) {
        try {
            if (result.hasErrors()) {
                // get messages error from BindingResult
                List<String> errorMessages = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
                // return messages error
                return ResponseEntity.badRequest().body(errorMessages);
            }
            String token = JwtService.extractToken(request);
            String username = JwtService.extractInfoFromToken(token);
            Chapter chapter = chapterService.getChapterById(chapterDTO.getChapter().getId());

            if(chapter == null) return ResponseEntity.status(404).body("Chapter Not found"); // check chapter

            if(chapter.getStory().getId() != chapterDTO.getStoryId())
                return ResponseEntity.badRequest().body("Can't delete chapter: chapters and stories are not in the same system");//chek story

            if(chapter.getStory().getUser() == null || !chapter.getStory().getUser().getUsername().equals(username))//check role
                return ResponseEntity.status(403).body("You are not Author of this story");
            chapterDTO.getChapter().setStory(chapter.getStory());  //set story before update
            chapterService.update(chapterDTO.getChapter());
            return ResponseEntity.ok().body("update success fully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("couldn't not add chapter: " + e.getMessage());
        }
    }

    //update Chapter(for admin)
    @PutMapping("/admin/updateChapter")
    public ResponseEntity<?> updateChapter(@RequestBody @Valid ChapterDTO chapterDTO,BindingResult result) {
        try {
            if (result.hasErrors()) {
                // get messages error from BindingResult
                List<String> errorMessages = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
                // return messages error
                return ResponseEntity.badRequest().body(errorMessages);
            }

            Chapter chapter = chapterService.getChapterById(chapterDTO.getChapter().getId());

            if(chapter == null) return ResponseEntity.status(404).body("Chapter Not found"); // check chapter

            if(chapter.getStory().getId() != chapterDTO.getStoryId())
                return ResponseEntity.badRequest().body("Can't delete chapter: chapters and stories are not in the same system");//chek story

            chapterDTO.getChapter().setStory(chapter.getStory());
            chapterService.update(chapterDTO.getChapter());
            return ResponseEntity.ok().body("update success fully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("couldn't not add chapter: " + e.getMessage());
        }
    }

}
