package com.example.TestProject.service;

import com.example.TestProject.dto.UserDTO;
import com.example.TestProject.entities.Employee;
import com.example.TestProject.entities.Role;
import com.example.TestProject.entities.User;
import com.example.TestProject.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor //is an annotation in the Lombok
// library used in Spring Boot to automatically generate a constructor
// that contains fields marked as final or annotated with @NonNull.
@Transactional  //start,comit,rollback
@Slf4j  //auto logger
public class UserService {
    private final UserRepository userRepository;
    private final StoryService storyService;

    //get list user
    public List<User> getAll() {

        return userRepository.findAll();
    }

    //get user by username
    public User getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findUserByUsername(username);
        if (userOptional.isPresent()) {
            User u;
            u = userOptional.get();
            return u;
        }
        return null;
    }

    //get user by nick_name
    public User findUserByNickName(String nickname) {
        Optional<User> user = userRepository.findUserByNickname(nickname);
        if (user.isPresent()) {
            User u;
            u = user.get();
            return u;
        }
        return null;
    }

    //update user
    public void update(UserDTO obj) {
        User userCheck = getUserByUsername(obj.getUser().getUsername());
        if (userCheck == null) throw new UsernameNotFoundException("User not found");

        if(!passwordEncoder.matches(obj.getUser().getPassword(),userCheck.getPassword()))
            throw new RuntimeException("The password does not match the current password");//check password

        if (!userCheck.getNickname().equals(obj.getUser().getNickname())) {//user change nickname
            if (findUserByNickName(obj.getUser().getNickname()) != null)
                throw new RuntimeException("nickname already exists");//check nickname
        }
        //set update
        userCheck.setEmail(obj.getUser().getEmail());
        userCheck.setBirthday(obj.getUser().getBirthday());
        userCheck.setNickname(obj.getUser().getNickname());
        Set<Role> listR = new HashSet<>();
        obj.getRoleName().forEach(roleName -> { //add role
            Role r = userRepository.getRoleByName(roleName);
            if (r != null) {
                listR.add(r);
            }
        });
        //set role
        userCheck.setRoles(listR);
        userRepository.save(userCheck);
    }

    //password encryption
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //create User
    public void save(UserDTO obj) {
        User u = obj.getUser();
        if(getUserByUsername(u.getUsername()) != null) throw new RuntimeException("Username alreadyExists"); //check username
        if(findUserByNickName(u.getNickname()) != null) throw new RuntimeException("nickname already is Exists");//check nickname
        Set<Role> listR = new HashSet<>();
        obj.getRoleName().forEach(roleName -> { //add role
            Role r = userRepository.getRoleByName(roleName);
            if (r != null) {
                listR.add(r);
            }
        });
        u.setRoles(listR);
        if (obj.getEmployeeId() != null) {//check emp and add
            Employee e = userRepository.getEmployeeById(obj.getEmployeeId());
            if(e == null) throw new RuntimeException("employee not found");
            u.setEmployee(e);
        }
        //password encryption
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        userRepository.save(u);
    }

    // Forgot Password
    public boolean recoveryPassword(User obj) {
        User user = getUserByUsername(obj.getUsername());
        //check information from input of client
        if (user != null) {
            if (user.getEmail().equals(obj.getEmail()) &&
                    user.getNickname().equals(obj.getNickname())) { //same all
                // password encryption
                user.setPassword(passwordEncoder.encode(obj.getPassword()));
                userRepository.save(user);
                return true;
            }
            return false;
        }
        return false;
    }

    //delete user
    public void deleteByUsername(String username) {
        User user = getUserByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not found");
        user.getRoles().clear();
        userRepository.deleteHistoryByUser(user.getId());//delete all history of user
        userRepository.deleteFavoriteByUser(user.getId());//delete all favorites of user
        userRepository.deleteCommentByUser(user.getId());//delete all comment of user
        userRepository.deleteHistoryByUser(user.getId());//delete All history of user
        userRepository.deleteById(user.getId());//delete user
    }

}
