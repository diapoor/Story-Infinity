package com.example.TestProject.controller;

import com.example.TestProject.dto.StoryRequestDTO;
import com.example.TestProject.entities.Story;
import com.example.TestProject.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/stories")
@Slf4j
@RequiredArgsConstructor
public class StoryController {
    private final StoryService storyService;

    //get list Story(public)
    @GetMapping("/public/list")
    public ResponseEntity<List<Story>> getStoriesPublic() {

        return ResponseEntity.ok().body(storyService.getAllModePublic());
    }

    //response image of Story
    @GetMapping("/public/{storyId}/image")
    public ResponseEntity<?> getImage(@PathVariable("storyId") int storyId) {
        try {
            String image = storyService.getImageOfStory(storyId);
            Path filePath = Path.of(image);
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(filePath));
            MediaType mediaType;
            if (image.toLowerCase().endsWith(".png")) mediaType = MediaType.IMAGE_PNG;
            else mediaType = MediaType.IMAGE_JPEG;
            return ResponseEntity.ok().contentType(mediaType).body(resource);
        } catch (NullPointerException e) {
            return ResponseEntity.status(404).body("null");
        } catch (IOException e) {
            return ResponseEntity.status(404).body("null ");
        }
    }

    //get details story (public)
    @GetMapping("/public/{storyId}/details")
    public ResponseEntity<?> detailsStory(@PathVariable("storyId") int storyId) {
        Story story = storyService.findStoryById(storyId);
        if (story == null) return ResponseEntity.status(404).body("Story not found");
        return ResponseEntity.ok().body(story);
    }

    // Search
    @GetMapping("/public/search")
    public ResponseEntity<?> searchStory(@RequestParam("keyword") String keyword) {
        List<Story> list = storyService.getListStoryByString(keyword);
        if (list.isEmpty()) return ResponseEntity.status(404).body("Story not found");
        return ResponseEntity.ok().body(list);
    }

    //get list for admin
    @GetMapping("/admin/list")
    public List<Story> getStoryByAdmin() {
        return storyService.getAllModeAdmin();
    }

    //create story for user
    @PostMapping(value = "/user/createStory")
    public ResponseEntity<?> createStoryByUser(HttpServletRequest request,
                                               @RequestPart("image") MultipartFile image,
                                               @ModelAttribute @Valid StoryRequestDTO storyRequestDTO,
                                               BindingResult result) {
        try {
            if (result.hasErrors()) {
                // get messages error from BindingResult
                List<String> errorMessages = result.getAllErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList());
                // return messages error
                return ResponseEntity.badRequest().body(errorMessages);
            }
            if (image == null) return ResponseEntity.badRequest().body("Please upload a photo that fits the story");

            String token = JwtService.extractToken(request);
            String username = JwtService.extractInfoFromToken(token);
            storyRequestDTO.setUsername(username);
            //set story
            storyService.saveStory(image, storyRequestDTO); //ioe Exception
            return ResponseEntity.ok().body("Story create successfully");

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("image not support");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("some problem");
        }
    }

    //create story for admin
    //editor,moderator,admin
    @PostMapping("/admin/createByAdmin")
    public ResponseEntity<?> createStoryByAdmin(@ModelAttribute @Valid StoryRequestDTO storyRequestDTO,
                                                BindingResult result,
                                                @RequestPart("image") MultipartFile image) {
        try {
            if (result.hasErrors()) {
                // get messages error from BindingResult
                List<String> errorMessages = result.getAllErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList());
                // return messages error
                return ResponseEntity.badRequest().body(errorMessages);
            }
            if (image == null) return ResponseEntity.badRequest().body("Please upload a photo that fits the story");
            storyService.saveStory(image, storyRequestDTO); //ioe Exception
            return ResponseEntity.ok().body("Story create successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Please check the genre name and author carefully");
        }
    }

    //update story for user
    //user
    @PutMapping("/user/update")
    public ResponseEntity<?> updateStory(@ModelAttribute @Valid StoryRequestDTO storyRequestDTO,
                                         HttpServletRequest request,
                                         @RequestParam("image") MultipartFile image,
                                         BindingResult result) {
        try {
            if (result.hasErrors()) {
                // get messages error from BindingResult
                List<String> errorMessages = result.getAllErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList());
                // return messages error
                return ResponseEntity.badRequest().body(errorMessages);
            }
            if (image == null)
                return ResponseEntity.badRequest().body("Please upload a photo that fits the story");//check image

            String token = JwtService.extractToken(request);
            String username = JwtService.extractInfoFromToken(token);
            storyRequestDTO.setUsername(username);
            storyService.updateStory(image, storyRequestDTO); //ioe Exception
            return ResponseEntity.ok().body("Story update successfully");
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().body("update failed");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("some problem");
        }
    }

    //update story for admin
    //edit,mod,admin
    @PutMapping("/admin/update")
    public ResponseEntity<?> updateStoryByAdmin(@ModelAttribute StoryRequestDTO storyRequestDTO,
                                                @RequestPart("image") MultipartFile image,
                                                BindingResult result) {
        try {
            if (result.hasErrors()) {
                // get messages error from BindingResult
                List<String> errorMessages = result.getAllErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList());
                // return messages error
                return ResponseEntity.badRequest().body(errorMessages);
            }
            if (image == null)
                return ResponseEntity.badRequest().body("Please upload a photo that fits the story");//check image
            storyRequestDTO.setUsername(null);
            storyService.updateStory(image, storyRequestDTO); //ioe Exception
            return ResponseEntity.ok().body("Story update successfully");
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().body("update failed");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("some problem");
        }
    }

    //delete story user
    //user
    @DeleteMapping("/user/{storyId}/delete")
    public ResponseEntity<?> deleteByUser(HttpServletRequest request, @PathVariable("storyId") int storyId) {
        try {
            String token = JwtService.extractToken(request);
            String username = JwtService.extractInfoFromToken(token);

            Story story = storyService.getStoryById(storyId);
            if (!story.getUser().getUsername().equals(username))
                return ResponseEntity.badRequest().body("Delete failed: You not are Author of this Story");
            storyService.deleteStoryById(story);
            return ResponseEntity.ok().body("deleted");

        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("Delete failed: Story not does exists.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Delete failed: Story not does exists." + e.getMessage());
        }
    }

    //delete story (admin)
    //edit.mod,admin
    @DeleteMapping("/admin/{storyId}/delete")
    public ResponseEntity<?> deleteByUser(@PathVariable("storyId") int storyId) {
        try {
            Story story = storyService.getStoryById(storyId);
            storyService.deleteStoryById(story);
            return ResponseEntity.ok().body("deleted");
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("Delete failed: Story not does exists.");
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().body("Delete failed");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("some problem");
        }


    }
}
