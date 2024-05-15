package com.example.TestProject.repositories;

import com.example.TestProject.entities.Genres;
import com.example.TestProject.entities.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenresRepository extends JpaRepository<Genres,Integer> {
    //get list story by genres
    @Query("SELECT s FROM Story s LEFT JOIN s.genres g WHERE g.id = :genresId")
    List<Story> getStoriesByGenres(@Param("genresId") int genresId);

    //public
    @Query("SELECT s FROM Story s LEFT JOIN s.genres g WHERE g.id = :genresId and s.status = true")
    List<Story> getStoriesByGenresPublic(@Param("genresId") int genresId);
    @Query("SELECT g FROM Genres g WHERE g.name = :name")
    Optional<Genres> findGenresByName(String name);
}
