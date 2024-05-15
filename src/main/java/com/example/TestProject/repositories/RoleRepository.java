package com.example.TestProject.repositories;

import com.example.TestProject.entities.Role;
import com.example.TestProject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.id = :roleId")
    User getFirstUserByRole(@Param("roleId") int roleId);

    Optional<Role> findRoleByName(String name);
}
