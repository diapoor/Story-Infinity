package com.example.TestProject.repositories;

import com.example.TestProject.entities.Author;
import com.example.TestProject.entities.Story;
import com.example.TestProject.service.AuthorService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {

    //get list story by author
    @Query("SELECT s FROM Story s LEFT JOIN s.authors a WHERE a.id = :authorId")
    List<Story> getStoriesByAuthorId(@Param("authorId") int authorId);
    Optional<Author> findAuthorByName(String name);
}
