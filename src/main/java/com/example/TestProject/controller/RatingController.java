package com.example.TestProject.controller;

import com.example.TestProject.dto.RatingDTO;
import com.example.TestProject.dto.RatingResponseDTO;
import com.example.TestProject.entities.Rating;
import com.example.TestProject.entities.Story;
import com.example.TestProject.entities.User;
import com.example.TestProject.service.JwtService;
import com.example.TestProject.service.RatingService;
import com.example.TestProject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/rating")
@RequiredArgsConstructor
@Slf4j
public class RatingController {
    private final RatingService ratingService;
    private final UserService userService;

    @GetMapping("/public/{storyId}/ratingpoint")
    public ResponseEntity<?> getRatingPoint(@PathVariable("storyId") int storyId) {
        try {
            Double avg = 0.0;
            avg += ratingService.getRatingPoint(storyId);
            return ResponseEntity.ok().body(avg);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("get Rating point failed: Story not found");
        }
    }

    //check rating (Admin)
    @GetMapping("/admin/{storyId}/allRating")
    public ResponseEntity<?> getAllRatingByStory(@PathVariable("storyId") int storyId) {
        try {
            List<Rating> ratingList = ratingService.getRatingOfStory(storyId);
            List<RatingResponseDTO> ratingResponseDTOS = ratingList.stream().map(rating -> {
                RatingResponseDTO ratingResponseDTO = new RatingResponseDTO();
                ratingResponseDTO.setId(rating.getId());
                ratingResponseDTO.setRatingPoint(rating.getRating());
                ratingResponseDTO.setNickname(rating.getUserRating().getNickname());
                return ratingResponseDTO;
            }).toList();
            return ResponseEntity.ok().body(ratingResponseDTOS);
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("story not found");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("some problem");
        }
    }


    @PostMapping("/user/addRating")
    public ResponseEntity<?> addRating(HttpServletRequest request, @RequestBody @Valid RatingDTO ratingDTO, BindingResult result) {
        try {
            if (result.hasErrors()) {
                // get messages error from BindingResult
                List<String> errorMessages = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
                // return messages error
                return ResponseEntity.badRequest().body(errorMessages);
            }
            String token = JwtService.extractToken(request);
            String username = JwtService.extractInfoFromToken(token);
            User user = userService.getUserByUsername(username);
            Rating rating = new Rating();
            Story story = ratingService.checkStory(ratingDTO.getStoryId());
            if (story == null) return ResponseEntity.badRequest().body("story not found");
            rating.setRating(ratingDTO.getRating());
            rating.setUserRating(user);
            rating.setStoryRating(story);
            ratingService.save(rating);
            return ResponseEntity.ok("oke");
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().body("couldn't rating");
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("not found");
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body("some problem");
        }
    }

    //delete rating folow story (only admin)
    @DeleteMapping("/admin/deleteByStory/{sId}")
    public ResponseEntity<?> deleteByStory(@PathVariable("sId") int sId) {
        try {
            ratingService.deleteByStory(sId);
            return ResponseEntity.ok().body("deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Can't delete");
        }
    }

    //delete rating folow  id(only admin)
    @DeleteMapping("/admin/deleteById/{rId}")
    public ResponseEntity<?> deleteById(@PathVariable("rId") int rId) {
        try {
            ratingService.deleteById(rId);
            return ResponseEntity.ok().body("deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Can't delete");
        }
    }
}
