package com.example.TestProject.service;

import com.example.TestProject.entities.Favorite;
import com.example.TestProject.entities.FavoriteId;
import com.example.TestProject.repositories.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;
    //get favorire list by user
    public List<Favorite> getAllFavoriteByUser(int userId) {
        return favoriteRepository.getListFavovoriteByUser(userId);
    }

    // check the existence of the favorite
    public boolean checkFavorite(int userId,int storyId) {
        Optional<Favorite> favoriteOptional = favoriteRepository.findFavoriteByUserAndStory(userId,storyId);
        return favoriteOptional.isPresent();
    }
    // add/delete Favorite by User
    public boolean save(Favorite favorite) {
        int userId = favorite.getUser().getId();
        int storyId = favorite.getStory().getId();
        //check favorite
        if(checkFavorite(userId,storyId)){ //delete if favorite already exists
            favoriteRepository.deleteFavorite(userId,storyId);
            return false;
        } else { //add if favorite not exists
            favorite.setId(new FavoriteId(userId,storyId));
            favoriteRepository.save(favorite);
            return true;
        }
    }

    //delete favorites by user when user want delete ALl or delete user
    public void deleteFavoriteByUser(int userId) {
        favoriteRepository.deleteAllByUser(userId);
    }

    //delete one
    public  void deleteOne(int userId,int storyId) {
        favoriteRepository.deleteOne(storyId,userId);
    }
}
