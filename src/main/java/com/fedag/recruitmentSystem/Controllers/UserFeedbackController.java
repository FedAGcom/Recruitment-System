package com.fedag.recruitmentSystem.Controllers;

import com.fedag.recruitmentSystem.Service.feedbackService.UserFeedbackService;
import com.fedag.recruitmentSystem.model.User;
import com.fedag.recruitmentSystem.model.UserFeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserFeedbackController {

    @Autowired
    UserFeedbackService userFeedbackService;

    @GetMapping("/feedback")
    public List<UserFeedback> showAllFeedback(){
        List<UserFeedback> allUserFeedback = userFeedbackService.findAllUserFeedback();
        return allUserFeedback;
    }

    @GetMapping("/feedback/{id}")
    public UserFeedback getUserFeedback(@PathVariable Long id){
        UserFeedback userFeedback = userFeedbackService.findUserFeedbackById(id);
        return userFeedback;
    }

    @PostMapping("/feedback")
    public void addNewUserFeedback(@RequestBody UserFeedback userFeedback){
        userFeedbackService.saveUserFeedback(userFeedback);
    }

    @PutMapping("/feedback")
    public void updateUserFeedback(@RequestBody UserFeedback userFeedback){
        userFeedbackService.saveUserFeedback(userFeedback);
    }

    @DeleteMapping("/feedback/{id}")
    public void deleteUserFeedback(@PathVariable Long id){
        userFeedbackService.deleteUserFeedbackById(id);
    }
}
