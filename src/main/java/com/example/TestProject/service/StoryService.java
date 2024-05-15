package com.example.TestProject.service;

import com.example.TestProject.dto.StoryRequestDTO;
import com.example.TestProject.entities.*;
import com.example.TestProject.repositories.StoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class StoryService {
    //path of image story
    @Value("${uploadImagesStory.path}")
    private String imagelinkPath;

    private final StoryRepository storyRepository;

    //get image of story
    public String getImageOfStory(int storyId) {
        Story story = findStoryById(storyId);
        if (story == null) return null;
        return imagelinkPath + "/" + story.getImage();
    }

    //get all lists story(admin)
    public List<Story> getAllModeAdmin() {
        return storyRepository.findAll();
    }

    //get List Story(public)
    public List<Story> getAllModePublic() {
        return storyRepository.getStoriesPublic();
    }

    //search story by name,author,title,user
    public List<Story> getListStoryByString(String keyword) {
        return storyRepository.getListStoryByKeyword(keyword);
    }


    //get story by id
    public Story findStoryById(int id) {
        Optional<Story> story = storyRepository.findById(id);
        if (story.isPresent()) {
            Story result;
            result = story.get();
            return result;
        } else {
            return null;
        }
    }


    //save story
    public void saveStory(MultipartFile imageName, StoryRequestDTO storyRequestDTO) throws IOException {

        Story story = new Story();
        if (storyRequestDTO.getUsername() != null) { //story from user
            User u = storyRepository.findUserByUsername(storyRequestDTO.getUsername());
            story.setUser(u); //set user
            story.setAuthors(null); //set author null
        } else {     //story from admin
            Set<Author> authorSet = new HashSet<>();
            storyRequestDTO.getAuthorName().forEach(name -> { //add role
                Author a = storyRepository.findAuthorByName(name);
                if (a != null) {
                    authorSet.add(a);
                }
            });
            if (authorSet.isEmpty()) throw new RuntimeException("Please check the Author name carefully");
            story.setAuthors(authorSet); //set author
            story.setUser(null); // set user null
        }

        story.setDatepost(storyRequestDTO.getDatePost());
        story.setProcess(storyRequestDTO.isProcess());
        story.setTitle(storyRequestDTO.getTitle());
        story.setDescription(storyRequestDTO.getDescription());
        story.setStatus(false);
        Set<Genres> genresSet = new HashSet<>();
        storyRequestDTO.getGenresName().forEach(name -> { //add role
            Genres g = storyRepository.findGenresByName(name);
            if (g != null) {
                genresSet.add(g);
            }
        });
        if (genresSet.isEmpty()) throw new RuntimeException("Please check the genre name carefully");
        story.setGenres(genresSet);
        //change image
        if (imageName != null && !imageName.isEmpty()) {
            // Kiểm tra loại file hoặc MIME type
            if (!imageName.getContentType().startsWith("image")) {
                throw new IllegalArgumentException("Only image files are allowed.");
            }

            // Tạo tên file duy nhất
            String newImageName = UUID.randomUUID().toString() + "_" +
                    StringUtils.cleanPath(imageName.getOriginalFilename().replaceAll("\\s+", ""));

            try {
                // Lưu file vào thư mục lưu trữ
                Path uploadDir = Paths.get(imagelinkPath);
                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }

                Path filePath = uploadDir.resolve(newImageName);
                imageName.transferTo(filePath);

                // Lưu tên file vào dữ liệu
                story.setImage(newImageName);
            } catch (IOException ex) {
                throw new IOException("Failed to store file: " + ex.getMessage());
            }
        }

        storyRepository.save(story);
    }

    //update story
    public void updateStory(MultipartFile imageName, StoryRequestDTO obj) throws IOException {
        Story story = getStoryById(obj.getStoryId());
        if (story == null) throw new IOException("Story does not exists."); //check story
        if(obj.getUsername() != null) {//from  user
            if(story.getUser() == null || !story.getUser().getUsername().equals(obj.getUsername()))
                throw new IOException("Update failed: you not are author of this story");
            story.getAuthors().clear();
        } else { //from admin
            story.getAuthors().clear();
            Set<Author> authorSet = new HashSet<>();
            obj.getAuthorName().forEach(name -> { //add author
                Author a = storyRepository.findAuthorByName(name);
                if (a != null) {
                    authorSet.add(a);
                }
            });
            if (authorSet.isEmpty()) throw new RuntimeException("Please check the Author name carefully");
            story.setAuthors(authorSet); //set author
        }
        story.setDatepost(obj.getDatePost());
        story.setProcess(obj.isProcess());
        story.setTitle(obj.getTitle());
        story.setDescription(obj.getDescription());
        story.setStatus(false);
        Set<Genres> genresSet = new HashSet<>();
        obj.getGenresName().forEach(name -> { //add role
            Genres g = storyRepository.findGenresByName(name);
            if (g != null) {
                genresSet.add(g);
            }
        });
        if (genresSet.isEmpty()) throw new RuntimeException("Please check the genre name carefully");
        story.getGenres().clear();
        story.setGenres(genresSet);
        // Kiểm tra và xử lý tệp ảnh nếu có
        if (imageName != null && !imageName.isEmpty()) {
            if (!imageName.getContentType().startsWith("image")) {
                throw new IllegalArgumentException("Only image files are allowed.");
            }
            String newImageName = UUID.randomUUID().toString() + "_" +
                    StringUtils.cleanPath(imageName.getOriginalFilename().replaceAll("\\s+", ""));
            try {
                Path uploadDir = Paths.get(imagelinkPath);
                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }

                Path filePath = uploadDir.resolve(newImageName);
                imageName.transferTo(filePath);

                // Xóa tệp ảnh cũ nếu có
                String oldImageName = story.getImage();
                if (oldImageName != null && !oldImageName.isEmpty()) {
                    Path oldImagePath = uploadDir.resolve(oldImageName);
                    if (Files.exists(oldImagePath)) {
                        Files.delete(oldImagePath);
                    }
                }
                // Lưu tên file mới vào câu chuyện
                story.setImage(newImageName);
            } catch (IOException ex) {
                throw new IOException("Failed to store file: " + ex.getMessage());
            }
        }
        storyRepository.save(story);
    }

    public Story getStoryById(int id) { //even status false or true
        Story story = storyRepository.getStorysById(id);
        if (story == null) return null;
        return story;
    }

    //delete story
    public void deleteStoryById(Story story) throws IOException {
        //delete rating
        storyRepository.deleteRatingByStoryId(story.getId());
        //delete favorite
        storyRepository.deleteFavoriteByStory(story.getId());
        //delete comment
        storyRepository.deleteCommentByStory(story.getId());

        //delete chapter
        storyRepository.deleteChapterByStory(story.getId());
        // delete image directory
        String imageName = story.getImage();
        if (imageName != null && !imageName.isEmpty()) {
            Path imagePath = Paths.get(imagelinkPath).resolve(imageName);
            if (Files.exists(imagePath)) {
                Files.delete(imagePath);
            }
        }
        story.getAuthors().clear();
        story.getGenres().clear();
        // delete story
        storyRepository.delete(story);

    }

    //delete Story by user(when delete user or user delete)
    public void deleteStoryByUserId(int userId) {
        List<Story> stories = storyRepository.getStoriesByUserId(userId);
        for (Story story : stories) {
            try {
                deleteStoryById(story);
            } catch (IOException e) {
                e.printStackTrace(); // Hoặc thực hiện xử lý ngoại lệ phù hợp tại đây
            }
        }
    }

}
