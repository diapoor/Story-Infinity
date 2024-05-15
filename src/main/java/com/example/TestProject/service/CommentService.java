package com.example.TestProject.service;

import com.example.TestProject.entities.Comment;
import com.example.TestProject.entities.Story;
import com.example.TestProject.entities.User;
import com.example.TestProject.repositories.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    //getCommentPublic
    public List<Comment> getCommentbyStory(int storyId) {

        return commentRepository.findCommentByStory(storyId);
    }

    //folow admin
    public List<Comment> getCommentbyAdmin(int storyId) {

        return commentRepository.findCommentByStoryAdmin(storyId);
    }


    //save comment
    public void save(Comment comment) {

        commentRepository.save(comment);
    }

    //delete comment(admin)
    public void deleteById(int id) {
        commentRepository.deleteById(id);
    }

    //delete by story(when delete story or delete All)
    public void deleteByStory(int storyId) {
        commentRepository.deleteAllByStory(storyId);
    }
}
