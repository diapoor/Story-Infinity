package com.example.TestProject.repositories;

import com.example.TestProject.entities.Employee;
import com.example.TestProject.entities.Rating;
import com.example.TestProject.entities.Role;
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
public interface UserRepository extends JpaRepository<User,Integer> {
 //get user by username
 @Query("SELECT u FROM  User  u WHERE u.username like :username")
 Optional<User> findUserByUsername(@Param("username") String username);

 //get user by nickname
 @Query("SELECT u FROM  User  u WHERE u.nickname like :nickname")
 Optional<User> findUserByNickname(@Param("nickname") String nickname);

 //get role
 @Query("SELECT r FROM Role r WHERE r.name LIKE :roleName")
 Role getRoleByName(@Param("roleName") String roleName);

 @Query("SELECT e from Employee e where e.id = :eId")
 Employee getEmployeeById(@Param("eId") int eId);




 //delete history by user
 @Modifying
 @Transactional
 @Query("DELETE FROM History h where h.userHistory.id = :userId")
 void deleteHistoryByUser(@Param("userId") int userId);

 //delete rating by  user
 @Modifying
 @Transactional
 @Query("DELETE FROM Rating r WHERE r.userRating.id = :userId")
 List<Rating> deleteAllByUserId(@Param("userId") int userId);

 //delete comment by user
 @Modifying
 @org.springframework.transaction.annotation.Transactional
 @Query("DELETE FROM Comment c WHERE c.user.id = :userId")
 void deleteCommentByUser(int userId);

 //delete favorite by user
 @Modifying
 @Transactional
 @Query("DELETE FROM Favorite f WHERE f.user.id = :userId")
 void deleteFavoriteByUser(@Param("userId") int userId);
}
