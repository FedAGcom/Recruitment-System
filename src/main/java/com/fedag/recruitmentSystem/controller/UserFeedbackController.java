package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.UserFeedback;
import com.fedag.recruitmentSystem.service.impl.UserFeedbackServiceImpl;
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
@RequestMapping("/api/users/feedbacks")
public class UserFeedbackController {

  private final UserFeedbackServiceImpl userFeedbackService;

  @GetMapping
  public Page<UserFeedback> showAllFeedback(@PageableDefault(size = 5) Pageable pageable) {
    return userFeedbackService.getAllUserFeedbacks(pageable);
  }

  @GetMapping("/{id}")
  public UserFeedback getUserFeedback(@PathVariable Long id) {
    return userFeedbackService.findById(id);
  }

  @PostMapping
  public void addNewUserFeedback(@RequestBody UserFeedback userFeedback) {
    userFeedbackService.save(userFeedback);
  }

  @PutMapping
  public void updateUserFeedback(@RequestBody UserFeedback userFeedback) {
    userFeedbackService.save(userFeedback);
  }

  @DeleteMapping("/{id}")
  public void deleteUserFeedback(@PathVariable Long id) {
    userFeedbackService.deleteById(id);
  }
}
