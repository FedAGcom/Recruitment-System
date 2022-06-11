package com.fedag.recruitmentSystem.email;

import com.fedag.recruitmentSystem.service.impl.CompanyServiceImpl;
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

    private final CompanyServiceImpl companyService;

    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code) {

        boolean isActivatedUser = userService.activateUser(code);
        if (isActivatedUser) {
            return "Activation success. Go to login page."; // redirect на страницу ввода логина и парол
        }
        boolean isActivatedCompany = companyService.activateCompany(code);
        if (isActivatedCompany) {
            return "Activation success. Go to login page."; // redirect на страницу ввода логина и парол
        }

        return "Activation is failed"; // redirect на страницу о том что активация не прошла
    }
}
