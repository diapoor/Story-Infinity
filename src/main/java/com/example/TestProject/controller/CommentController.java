package com.example.TestProject.controller;

import com.example.TestProject.dto.CommentRequestDTO;
import com.example.TestProject.dto.CommentResponseDTO;
import com.example.TestProject.entities.Comment;
import com.example.TestProject.entities.Story;
import com.example.TestProject.entities.User;
import com.example.TestProject.service.CommentService;
import com.example.TestProject.service.JwtService;
import com.example.TestProject.service.StoryService;
import com.example.TestProject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/comment")
@Slf4j
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;
    private final StoryService storyService;

    //get comment public
    @GetMapping("/public/{storyId}/comment")
    public ResponseEntity<?> getComment(@PathVariable("storyId") int storyId) {
        try {
            List<Comment> listC = commentService.getCommentbyStory(storyId);
            List<CommentResponseDTO> commentResponseDTOS = new ArrayList<>();

            listC.forEach(comment -> {
                CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
                commentResponseDTO.setId(comment.getId());
                commentResponseDTO.setNicknameComment(comment.getUser().getNickname());
                commentResponseDTO.setContent(comment.getContent());
                commentResponseDTO.setDate(comment.getDateComment());
                commentResponseDTOS.add(commentResponseDTO);
            });
            return ResponseEntity.ok(commentResponseDTOS);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //get comment for admin
    @GetMapping("/admin/{storyId}/list")
    public ResponseEntity<?> getCommentFormAdmin(@PathVariable("storyId") int storyId) {
        try {
            List<Comment> listC = commentService.getCommentbyAdmin(storyId);
            List<CommentResponseDTO> commentResponseDTOS = new ArrayList<>();
            listC.forEach(comment -> {
                CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
                commentResponseDTO.setId(comment.getId());
                commentResponseDTO.setNicknameComment(comment.getUser().getNickname());
                commentResponseDTO.setContent(comment.getContent());
                commentResponseDTO.setDate(comment.getDateComment());
                commentResponseDTOS.add(commentResponseDTO);
            });
            return ResponseEntity.ok(commentResponseDTOS);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //create comment
    @PostMapping("/user/create")
    public ResponseEntity<?> createComment(@RequestBody @Valid CommentRequestDTO commentRequestDTO, BindingResult result, HttpServletRequest request) {
        try {
            if (result.hasErrors()) {
                // get messages error from BindingResult
                List<String> errorMessages = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
                // return messages error
                return ResponseEntity.badRequest().body(errorMessages);
            }
            String token = JwtService.extractToken(request);
            String username = JwtService.extractInfoFromToken(token);
            User user;
            user=userService.getUserByUsername(username);
            Story story = storyService.findStoryById(commentRequestDTO.getStoryId());
            if (story == null) {
                return ResponseEntity.badRequest().body("story not found");
            }
            Comment comment = new Comment();
            comment.setUser(user);
            comment.setStory(story);
            comment.setContent(commentRequestDTO.getContent());
            commentService.save(comment);
            return  ResponseEntity.ok().body("Comment is now valid");
        } catch (
                ConstraintViolationException e) {
            return ResponseEntity.badRequest().body("Invalid data: " + e.getMessage());
        } catch (
                DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Comment Invalid");
        } catch (
                Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }

    }

    //delete comment(admin)
    @DeleteMapping("/admin/delete")
    public ResponseEntity<?> delete(@RequestBody @Valid CommentRequestDTO commentRequestDTO,BindingResult result) {
        if (result.hasErrors()) {
            // get messages error from BindingResult
            List<String> errorMessages = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
            // return messages error
            return ResponseEntity.badRequest().body(errorMessages);
        }
        commentService.deleteByStory(commentRequestDTO.getStoryId());
        return ResponseEntity.ok().body("deleted All");
    }
}
