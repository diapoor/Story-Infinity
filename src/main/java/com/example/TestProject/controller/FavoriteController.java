package com.example.TestProject.controller;

import com.example.TestProject.dto.FavoriteDTO;
import com.example.TestProject.entities.Favorite;
import com.example.TestProject.entities.Story;
import com.example.TestProject.entities.User;
import com.example.TestProject.service.FavoriteService;
import com.example.TestProject.service.JwtService;
import com.example.TestProject.service.StoryService;
import com.example.TestProject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/favorite")
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FavoriteController {
    private final FavoriteService favoriteService;

    private final UserService userService;

    private final StoryService storyService;

    //get List
    @GetMapping("/list")
    public ResponseEntity<?> getListFavoriteByUser(HttpServletRequest request) {
        try {
            String token = JwtService.extractToken(request);
            String username = JwtService.extractInfoFromToken(token);
            User user = userService.getUserByUsername(username);
            //convert Favorite to FavoriteDTO
            List<Favorite> favorites = favoriteService.getAllFavoriteByUser(user.getId());
            List<FavoriteDTO> favoriteDTOS = favorites.stream().map(favorite -> {
                FavoriteDTO favoriteDTO = new FavoriteDTO();
                favoriteDTO.setStoryId(favorite.getStory().getId());
                favoriteDTO.setStoryName(favorite.getStory().getTitle());
                return favoriteDTO;
            }).toList();
            //return List FavoriteDTO
            return ResponseEntity.ok().body(favoriteDTOS);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("some problem");
        }
    }

    //add favorite
    @PostMapping("/add")
    public ResponseEntity<?> addFavorite(HttpServletRequest request, @RequestBody @Valid FavoriteDTO favoriteDTO, BindingResult result) {
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
            Story story = storyService.findStoryById(favoriteDTO.getStoryId());
            Favorite favorite = new Favorite(user, story);
            //add factory
            //check factory
            if (favoriteService.save(favorite)) { //save if factory not exists
                return ResponseEntity.ok().body("add successfully");
            } else {//delete if factory already exists
                return ResponseEntity.ok().body("remove successfully");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Oops,Can't add this story");
        }
    }

    //delete all by User
    @DeleteMapping("/deleteByUser")
    public ResponseEntity<String> deleteFavoriteByUser(HttpServletRequest request) {
        try {
            //check userId form token
            String token = JwtService.extractToken(request);
            String username = JwtService.extractInfoFromToken(token);
            User user = userService.getUserByUsername(username);
            favoriteService.deleteFavoriteByUser(user.getId());
            return ResponseEntity.ok().body("delete successfully");

        } catch (Exception e) {
            return ResponseEntity.ok().body(e.getMessage());
        }
    }

    //delete one
    @DeleteMapping("/deleteOne")
    public ResponseEntity<?> deleteFavoriteByUser(HttpServletRequest request, @RequestBody @Valid FavoriteDTO favoriteDTO, BindingResult result) {
        try {
            if (result.hasErrors()) {
                // get messages error from BindingResult
                List<String> errorMessages = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
                // return messages error
                return ResponseEntity.badRequest().body(errorMessages);
            }
            //check userId form token
            String token = JwtService.extractToken(request);

            String username = JwtService.extractInfoFromToken(token);
            User user = userService.getUserByUsername(username);
            favoriteService.deleteOne(user.getId(), favoriteDTO.getStoryId());
            return ResponseEntity.ok().body("delete successfully");

        } catch (Exception e) {
            return ResponseEntity.ok().body(e.getMessage());
        }
    }
}
