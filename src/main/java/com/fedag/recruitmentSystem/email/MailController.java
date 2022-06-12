package com.fedag.recruitmentSystem.email;

import com.fedag.recruitmentSystem.security.security_exception.ActivationException;
import com.fedag.recruitmentSystem.service.impl.CompanyServiceImpl;
import com.fedag.recruitmentSystem.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> activate(@PathVariable String code) {

        boolean isActivatedUser = userService.activateUser(code);
        if (isActivatedUser) {
            return new ResponseEntity<>("Activation success. Go to login page.",
                    HttpStatus.OK); // redirect на страницу ввода логина и пароля
        }
        try {
            boolean isActivatedCompany = companyService.activateCompany(code);
            if (isActivatedCompany) {
                return new ResponseEntity<>("Activation success. Go to login page.",
                        HttpStatus.OK); // redirect на страницу ввода логина и пароля
            }
        } catch (ActivationException e) {
            return new ResponseEntity<>(e.getMessage(),
                    HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>("Ошибка", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
