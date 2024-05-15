package com.example.TestProject.service;

import com.example.TestProject.entities.Rating;
import com.example.TestProject.entities.Story;
import com.example.TestProject.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    //check story status
    public Story checkStory(int sId) {
        return ratingRepository.findStoryById(sId);
    }
    //get point avg of story
    public Double getRatingPoint(int storyId) {
        if(ratingRepository.findStoryById(storyId) == null)//check story
            throw new RuntimeException("Story not found");
        Double avg = ratingRepository.findAverageRatingByStoryId(storyId);
        if(avg == null) return 0.0;
        return avg;
    }

    //get rating (admin)

    public List<Rating> getRatingOfStory(int storyId) {
        return ratingRepository.findRatingByStory(storyId);
    }

    //save rating

    public void save(Rating rating) {
        ratingRepository.save(rating);
    }

    //delete rating by Story
    public  void deleteByStory(int storyId) {
        ratingRepository.deleteAllByStoryId(storyId);
    }
    //delete rating by id
    public  void deleteById(int rId) {
        ratingRepository.deleteById(rId);
    }

}
