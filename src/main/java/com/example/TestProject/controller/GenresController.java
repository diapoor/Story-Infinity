package com.example.TestProject.controller;

import com.example.TestProject.entities.Author;
import com.example.TestProject.entities.Genres;
import com.example.TestProject.entities.Story;
import com.example.TestProject.service.GenresService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/genres")
@RequiredArgsConstructor
public class GenresController {
    private final GenresService genresService;

    //get List genres
    @GetMapping("/list")
    public ResponseEntity<List<Genres>> getAll() {

        return ResponseEntity.ok().body(genresService.findAllGenres());
    }

    //search Genres by name
    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam("name") String name) {
        Genres result = genresService.findGenresByName(name);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no matching genres");
        }
    }

    //get Genres by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getGenresById(@PathVariable("id") int id) {
        Genres genres = genresService.findGenresById(id);
        if(genres == null) return ResponseEntity.status(404).body("Genres not found");
        return ResponseEntity.ok().body(genres);
    }

    //get storyByGenres
    @GetMapping("/{genresId}/listStory")
    public ResponseEntity<List<Story>> getListStoryByGenres(@PathVariable("genresId") int genresId) {
        return ResponseEntity.ok().body(genresService.getAllStoryByGenres(genresId));
    }

    //get story by genres (public)
    @GetMapping("/public/{genresId}/listStory")
    public ResponseEntity<List<Story>> getListStoryByGenresPublic(@PathVariable("genresId") int genresId) {
        return ResponseEntity.ok().body(genresService.getAllStoryByGenresPublic(genresId));
    }

    //create Genres
    @PostMapping("/create")
    public ResponseEntity<?> createGenres(@RequestBody @Valid Genres obj, BindingResult result) {
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
            genresService.save(obj);
            return ResponseEntity.ok("Create genres successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("create Genres failed");
        }
    }

    //update Genre
    @PutMapping("/update")
    public ResponseEntity<?> updateGenre(@RequestBody @Valid Genres genres, BindingResult result) {
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
            if (genres.getId() < 1)
                return ResponseEntity.status(404).body("Genre not found");
            genresService.updateGenre(genres);
            return ResponseEntity.ok().body("update successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("couldn't update Genre: "+e.getMessage());
        }

    }

    //delete Genres
    @DeleteMapping("/delete/{gId}")
    public ResponseEntity<?> deleteGenres(@PathVariable("gId") int gId) {
        try {

            genresService.delete(gId);
            return ResponseEntity.ok("delete genres successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("couldn't delete Genres: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("couldn't delete Genres: ");
        }
    }
}
