package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.service.userService.UserService;
import com.fedag.recruitmentSystem.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public Page<User> showAllUsers(@PageableDefault(size = 5) Pageable pageable) {
        return userService.findAllUsers(pageable);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        User user = userService.findUserById(id);
        return user;
    }

    @PostMapping("/")
    public void addNewUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @PutMapping("/")
    public void updateUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }


}
