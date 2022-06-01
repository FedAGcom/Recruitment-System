package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.User;
import com.fedag.recruitmentSystem.service.CompanyFeedBackService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

  private final CompanyFeedBackService.UserService userService;

  @GetMapping
  public Page<User> showAllUsers(@PageableDefault(size = 5) Pageable pageable) {
    return userService.findAllUsers(pageable);
  }

  @GetMapping("/{id}")
  public User getUser(@PathVariable Long id) {
    return userService.findUserById(id);
  }

  @PostMapping
  public void addNewUser(@RequestBody User user) {
    userService.saveUser(user);
  }

  @PutMapping
  public void updateUser(@RequestBody User user) {
    userService.saveUser(user);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable Long id) {
    userService.deleteUserById(id);
  }
}
