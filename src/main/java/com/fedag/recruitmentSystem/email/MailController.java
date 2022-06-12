package com.fedag.recruitmentSystem.email;

import com.fedag.recruitmentSystem.security.security_exception.ActivationException;
import com.fedag.recruitmentSystem.service.impl.CompanyServiceImpl;
import com.fedag.recruitmentSystem.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Контроллер для активации по email", description = "Работа с почтой")
public class MailController {

    @Schema(name = "Сервис пользователей", description = "Содержит имплементацию методов для работы с репозиторием")
    private final UserServiceImpl userService;

    @Schema(name = "Сервис компаний", description = "Содержит имплементацию методов для работы с репозиторием")
    private final CompanyServiceImpl companyService;

    @Operation(summary = "Активация учетной записи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь зашел в учетную запись",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "403", description = "Ошибка ввода данных",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
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
