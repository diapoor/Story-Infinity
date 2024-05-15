package com.example.TestProject.service;

import com.example.TestProject.entities.Author;
import com.example.TestProject.entities.Genres;
import com.example.TestProject.entities.Story;
import com.example.TestProject.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService{
    private final AuthorRepository authorRepository;

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    //get Author by name
    public Author findAuthorByName(String name) {
        Optional<Author> author = authorRepository.findAuthorByName(name);
        if(author.isPresent()) {
            Author result = author.get();
            return result;
        } else {
            return null;
        }
    }
    //get story by author
    public List<Story> getStoriesByAuthor(int authorId) {
        return authorRepository.getStoriesByAuthorId(authorId);
    }

    public Author getOneById(int id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isPresent()) {
            Author author;
            author = authorOptional.get();
            return  author;
        }
        return null;
    }


    public void save(Author obj) {
        authorRepository.save(obj);
    }

    public void updateAuthor(Author obj) {
        if(getOneById(obj.getId()) == null) throw new RuntimeException("Author not found");
        authorRepository.save(obj);
    }
    public void deleteById(int id) {
        Author author = getOneById(id);
        if (author == null)
            throw new RuntimeException("Author not does Exists"); // Unable to find author with specific ID
        if(!authorRepository.getStoriesByAuthorId(author.getId()).isEmpty())
            throw new RuntimeException("Please delete stories related to the author first");//Can't delete author because story still exists
        authorRepository.deleteById(id);// Successfully deleted
    }

}
