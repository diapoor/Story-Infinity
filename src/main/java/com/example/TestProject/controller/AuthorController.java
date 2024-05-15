package com.example.TestProject.controller;

import com.example.TestProject.entities.Author;
import com.example.TestProject.entities.Story;
import com.example.TestProject.service.AuthorService;
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
@RequestMapping("/api/v1/author")
@RequiredArgsConstructor
@Slf4j
@Transactional
//all admin
public class AuthorController {
    private final AuthorService authorService;

    //get all Author
    @GetMapping("/list")
    public List<Author> getAll() {
        return authorService.getAll();
    }

    //get Author by name
    @GetMapping("/search")
    public Author getAuthorByName(@RequestParam("name") String name) {
        return authorService.findAuthorByName(name);
    }

    //get Author by id
    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable("id") int id) {
        return authorService.getOneById(id);
    }


    //get Story by Author
    @GetMapping("/{authorId}/list")
    public ResponseEntity<List<Story>> getStoriesByAuthor(@PathVariable("authorId") int authorId) {
        return ResponseEntity.ok().body(authorService.getStoriesByAuthor(authorId));
    }

    //add Author
    @PostMapping("/add")
    public ResponseEntity<?> createAuthor(@RequestBody @Valid Author author, BindingResult result) {
        try {
            if (result.hasErrors()) {
                // get messages error from BindingResult
                List<String> errorMessages = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
                // return messages error
                return ResponseEntity.badRequest().body(errorMessages);
            }
            authorService.save(author);
            return ResponseEntity.ok().body("create Author successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Add Author failed");
        }

    }


    //update Author
    @PutMapping("/update")
    public ResponseEntity<?> updateAuthor(@RequestBody @Valid Author author, BindingResult result) {
        try {
            if (result.hasErrors()) {
                // get messages error from BindingResult
                List<String> errorMessages = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
                // return messages error
                return ResponseEntity.badRequest().body(errorMessages);
            }
            if (author.getId() < 1) return ResponseEntity.notFound().build();
            authorService.updateAuthor(author);
            return ResponseEntity.ok().body("update successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("couldn't update Author: " + e.getMessage());
        }
    }

    //delete author
    @DeleteMapping("/delete/{aId}")
    public ResponseEntity<?> deleteAuthorById(@PathVariable("aId") int aId) {
        try {

            if (aId > 0) {
                authorService.deleteById(aId);
                return ResponseEntity.ok().body("delete successfully");
            }
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Couldn't delete Authorr: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("some problem");
        }
    }
}
