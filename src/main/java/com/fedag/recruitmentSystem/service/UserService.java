package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    Page<User> findAllUsers(Pageable pageable);

    User findUserById(Long id);

    void saveUser(User user);

    void deleteUserById(Long id);
}