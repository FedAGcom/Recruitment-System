package com.fedag.recruitmentSystem.email;

import com.fedag.recruitmentSystem.security.SecurityService;
import com.fedag.recruitmentSystem.service.impl.CompanyServiceImpl;
import com.fedag.recruitmentSystem.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Контроллер для активации по email", description = "Работа с почтой")
public class MailController {

    @Schema(name = "Сервис пользователей", description = "Содержит имплементацию методов для работы с репозиторием")
    private final UserServiceImpl userService;

    @Schema(name = "Сервис компаний", description = "Содержит имплементацию методов для работы с репозиторием")
    private final CompanyServiceImpl companyService;

    @Schema(name = "Сервис безопастности", description = "Содержит имплементацию методов для работы с авторизацией")
    private final SecurityService securityService;

    @Operation(summary = "Активация учетной записи пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь зашел в учетную запись",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @GetMapping("/activate/user/{code}")
    public ResponseEntity<?> activateUser(@PathVariable String code) {
        try {
            userService.activateUser(code);
            return new ResponseEntity<>("Activation success. Go to login page.",
                    HttpStatus.OK); // redirect на страницу ввода логина и пароля
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Активация учетной записи компании")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Компания зашел в учетную запись",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @GetMapping("/activate/company/{code}")
    public ResponseEntity<?> activateCompany(@PathVariable String code) {
        try {
            companyService.activateCompany(code);
            return new ResponseEntity<>("Activation success. Go to login page.",
                    HttpStatus.OK); // redirect на страницу ввода логина и пароля
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Подтверждение пароля польователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Компания зашел в учетную запись",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @GetMapping("/activate/user/password/{id}/{password}")
    public ResponseEntity<?> confirmUserPasswordChange(@PathVariable Long id, @PathVariable String password) {
        userService.confirmPasswordChange(id, password);
        return new ResponseEntity<>("Password has been changed successfully.",
                HttpStatus.OK);
    }

    @Operation(summary = "Подтверждение пароля компании")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Компания зашел в учетную запись",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @GetMapping("/activate/company/password/{id}/{password}")
    public ResponseEntity<?> confirmCompanyPasswordChange(@PathVariable Long id, @PathVariable String password) {
        companyService.confirmPasswordChange(id, password);
        return new ResponseEntity<>("Password has been changed successfully.",
                HttpStatus.OK);
    }

    @Operation(summary = "Подтверждение восстановления аккаунта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Компания зашел в учетную запись",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @GetMapping("/activate/restore/{email}")
    public ResponseEntity<?> accountRestore(@PathVariable String email) {
        return securityService.reactivateAccount(email);
    }
}
