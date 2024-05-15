package com.example.TestProject.controller;

import com.example.TestProject.dto.HistoryRequestDTO;
import com.example.TestProject.dto.HistoryResponseDTO;
import com.example.TestProject.entities.Chapter;
import com.example.TestProject.entities.History;
import com.example.TestProject.entities.User;
import com.example.TestProject.service.ChapterService;
import com.example.TestProject.service.HistoryService;
import com.example.TestProject.service.JwtService;
import com.example.TestProject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/history")
@Slf4j
@RequiredArgsConstructor
public class HistoryController {
    private final UserService userService;
    private final HistoryService historyService;
    private final ChapterService chapterService;

    //get History By User
    @GetMapping("/list")
    public ResponseEntity<?> getHistoriesByUser(HttpServletRequest request) {
        try {
            String token = JwtService.extractToken(request);
            String username = JwtService.extractInfoFromToken(token);
            User user = userService.getUserByUsername(username);
            List<HistoryResponseDTO> historyResponseDTOS = historyService.checkHistoryUser(user.getId());
            return ResponseEntity.ok().body(historyResponseDTOS);

        } catch (Exception e) {
            Logger.getLogger("An error occurred while processing the request: " + e.getMessage());
            return ResponseEntity.badRequest().body("An error occurred while processing the request.");
        }
    }


    //create history
    @PostMapping("/add")
    public ResponseEntity<?> addHistory(HttpServletRequest request, @RequestBody HistoryRequestDTO historyRequestDTO) {
        try {
            //check user
            String token = JwtService.extractToken(request);

            if (token != null) {
                String username = JwtService.extractInfoFromToken(token);
                User user = userService.getUserByUsername(username);
                if (historyRequestDTO.getChapterId() < 1)
                    return ResponseEntity.badRequest().body("Please confirm the indicators carefully");
                Chapter chapter = chapterService.getChapterById(historyRequestDTO.getChapterId());
                if (chapter == null)
                    return ResponseEntity.status(404).body("Can't create History because chapter not found");
                historyService.save(new History(user, chapter));
                return ResponseEntity.ok().body("successfully");
            } else {//no add history if user not login
                return ResponseEntity.ok().body("ok");
            }

        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("Please confirm the indicators carefully: ChapterId");
        } catch (Exception e) {
            Logger.getLogger(e.getMessage());
            return ResponseEntity.badRequest().body("add failed");
        }
    }

    //delete by user
    @DeleteMapping("/deleteByUser")
    public ResponseEntity<?> deleteHistoryByUser(HttpServletRequest request) {
        try {

            String token = JwtService.extractToken(request);
            String username = JwtService.extractInfoFromToken(token);
            User user = userService.getUserByUsername(username);
            historyService.deleteHistoryByUser(user.getId());
            return ResponseEntity.ok().body("delete successfully");

        } catch (Exception e) {
            Logger.getLogger(e.getMessage());
            return ResponseEntity.badRequest().body("some problem");
        }

    }

    //delete by id
    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteById(@RequestBody HistoryRequestDTO obj, HttpServletRequest request) {
        try {
            String token = JwtService.extractToken(request);
            String username = JwtService.extractInfoFromToken(token);

            if (obj.getHistoryId() < 1) //check id
                return ResponseEntity.badRequest().body("History not valid");
            History history = historyService.getOneById(obj.getHistoryId());
            if(history == null || !history.getUserHistory().getUsername().equals(username)) // check history and role
                return ResponseEntity.badRequest().body("Can't delete history: Please check history again" );
            return ResponseEntity.ok().body("deleted");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("some problem.");
        }
    }
}
