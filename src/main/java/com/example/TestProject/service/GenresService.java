package com.example.TestProject.service;

import com.example.TestProject.entities.Author;
import com.example.TestProject.entities.Genres;
import com.example.TestProject.entities.Story;
import com.example.TestProject.repositories.GenresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenresService {
    private final GenresRepository genresRepository;
    //get Genres by name
    public Genres findGenresByName(String name) {
        Optional<Genres> genres = genresRepository.findGenresByName(name);
        if(genres.isPresent()) {
            Genres result;
            result= genres.get();
            return result;
        } else {
            return null;
        }
    }
    //get story by genres
    public List<Story> getAllStoryByGenres(int genresId){
        return  genresRepository.getStoriesByGenres(genresId);
    }
    //get story by genres public
    public List<Story> getAllStoryByGenresPublic(int genresId){
        return  genresRepository.getStoriesByGenresPublic(genresId);
    }

    //get Genres by id
    public Genres findGenresById(int id) {
        Optional<Genres> genres = genresRepository.findById(id);
        if(genres.isPresent()) {
            Genres result;
            result= genres.get();
            return result;
        } else {
            return null;
        }
    }
    //get all of genres
    public List<Genres> findAllGenres() {

        return genresRepository.findAll();
    }
    public Genres findGenreById(int id) {
        Optional<Genres> optionalGenres = genresRepository.findById(id);
        if(optionalGenres.isPresent()) {
            Genres genres;
            genres = optionalGenres.get();
            return genres;
        }
        return null;
    }
    //save genres
    public void save(Genres obj) {
        if(findGenresByName(obj.getName()) != null) throw  new RuntimeException("Genres already exists");//check name genres
        genresRepository.save(obj);
    }

    //update genres

    public void updateGenre(Genres obj) {
        Genres genresCheck = findGenreById(obj.getId());
        if(genresCheck == null)  //check genres
            throw new RuntimeException("Genre not found.");
        // check update name
        if(!genresCheck.getName().equals(obj.getName())) {//if yes
            if(findGenresByName(obj.getName()) != null) throw  new RuntimeException("genre name is already exists");//check name genres
            genresRepository.save(obj);
        } else {//if no
            genresRepository.save(obj);
        }
    }
    //delete genres
    public void delete(int gId) {
        if(!genresRepository.getStoriesByGenres(gId).isEmpty())
            throw new RuntimeException("Please delete stories related to this genre first");//Can't delete genres because story still exists
        genresRepository.deleteById(gId);// Successfully deleted
    }
}
