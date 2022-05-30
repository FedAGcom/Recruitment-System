package com.fedag.recruitmentSystem.Service.userService;

import com.fedag.recruitmentSystem.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {

    List<User> findAllUsers();


    User findUserById(Long id);


    void saveUser(User user);


    void deleteUserById(Long id);
}
