package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.service.CompanyService;
import com.fedag.recruitmentSystem.model.UserFeedback;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feedback")
public class UserFeedbackController {

    private final CompanyService.UserFeedbackService userFeedbackService;

    @GetMapping
    public Page<UserFeedback> showAllFeedback(@PageableDefault(size = 5) Pageable pageable) {
        return userFeedbackService.findAllUserFeedback(pageable);
    }

    @GetMapping("/{id}")
    public UserFeedback getUserFeedback(@PathVariable Long id) {
        UserFeedback userFeedback = userFeedbackService.findUserFeedbackById(id);
        return userFeedback;
    }

    @PostMapping("/")
    public void addNewUserFeedback(@RequestBody UserFeedback userFeedback) {
        userFeedbackService.saveUserFeedback(userFeedback);
    }

    @PutMapping("/")
    public void updateUserFeedback(@RequestBody UserFeedback userFeedback) {
        userFeedbackService.saveUserFeedback(userFeedback);
    }

    @DeleteMapping("/{id}")
    public void deleteUserFeedback(@PathVariable Long id) {
        userFeedbackService.deleteUserFeedbackById(id);
    }
}
