package com.fedag.recruitmentSystem.email;

import com.fedag.recruitmentSystem.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MailController {

    private final UserServiceImpl userService;

    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code) {
        boolean isActivated = userService.activateUser(code);
        if (!isActivated) {
            return "Activation is failed"; // redirect на страницу о том что активация не прошла
        }
        return "Activation success. Go to login page."; // redirect на страницу ввода логина и пароля
    }


}
