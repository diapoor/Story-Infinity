package com.example.TestProject.service;

import com.example.TestProject.dto.HistoryResponseDTO;
import com.example.TestProject.entities.History;
import com.example.TestProject.repositories.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HistoryService {
    @Autowired
    private HistoryRepository historyRepository;

    //get history of user
    public List<HistoryResponseDTO> checkHistoryUser(int userId) {

        List<History> histories = historyRepository.findHistoryByUserHistory(userId);
        List<HistoryResponseDTO> historyResponseDTOS = new ArrayList<>();

        histories.forEach(history -> {
            HistoryResponseDTO historyResponseDTO = new HistoryResponseDTO();
            historyResponseDTO.setHistoryId(history.getId());
            historyResponseDTO.setStoryId(history.getChapterHistory().getStory().getId());
            historyResponseDTO.setChapterName(history.getChapterHistory().getTitle());
            historyResponseDTO.setStoryName(history.getChapterHistory().getStory().getTitle());
            historyResponseDTO.setReadAt(history.getReadedAt());
            historyResponseDTOS.add(historyResponseDTO);
        });
        return historyResponseDTOS;
    }
    //get history by id
    public History getOneById(int id) {
        Optional<History> historyOptional = historyRepository.findById(id);
        if(historyOptional.isPresent()) {
            History history;
            history = historyOptional.get();
            return history;
        } else {
            return null;
        }
    }

    //save history
    public void save(History history) {
        historyRepository.save(history);
    }

    //delete History By user(When delete All or delete user)
    public void deleteHistoryByUser(int userId) {
        historyRepository.deleteAllByUser(userId);
    }

    //delete by id
    public void deleteHistoryById(int id) {
        historyRepository.deleteById(id);
    }
}