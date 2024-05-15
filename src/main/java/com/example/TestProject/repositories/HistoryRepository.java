package com.example.TestProject.repositories;

import com.example.TestProject.entities.History;
import com.example.TestProject.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface HistoryRepository extends JpaRepository<History,Integer> {
    //get history of user
    @Query("SELECT h FROM History h WHERE h.userHistory.id  = :userId ORDER BY h.readedAt DESC ")
    List<History> findHistoryByUserHistory(@Param("userId") int userId);

    //delete by user
    @Modifying
    @Transactional
    @Query("DELETE FROM History h where h.userHistory.id = :userId")
    void deleteAllByUser(@Param("userId") int userId);

}
