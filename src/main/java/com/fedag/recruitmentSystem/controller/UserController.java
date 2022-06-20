package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.dto.request.UserChangePasswordRequest;
import com.fedag.recruitmentSystem.dto.request.UserRequest;
import com.fedag.recruitmentSystem.dto.request.UserUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.UserResponse;
import com.fedag.recruitmentSystem.enums.Role;
import com.fedag.recruitmentSystem.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Tag(name = "Контроллер пользователей", description = "Работа с пользователями")
public class UserController {

    @Schema(name = "Сервис пользователей", description = "Содержит имплементацию методов для работы с репозиторием")
    private final UserServiceImpl userService;

    @Operation(summary = "Получение списка пользователей", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список загружен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('READ')")
    @GetMapping
    public Page<UserResponse> getAllUsers(@PageableDefault(size = 5) Pageable pageable) {
        return userService.getAllUsers(pageable);
    }

    @Operation(summary = "Сортировка списка пользователей по вступительным экзаменам",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список отсортирован",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/filter")
    public List<UserResponse> findByEntranceExamScore(@RequestParam(defaultValue = "0",
            required = false) int score) {
        return userService.getByEntranceExamScore(score);
    }

    @Operation(summary = "Получение пользователя по id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь получен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @Operation(summary = "Добавление пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Пользователь добавлен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PostMapping
    public ResponseEntity<?> addNewUser(@Valid @RequestBody UserRequest user) {
        try {
            userService.save(user);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("User has been added successfully." +
                " Please check your email to confirm the registration.",
                HttpStatus.OK); //redirect /api/success-registration
    }

    @Operation(summary = "Изменение пароля пользователя", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пароль пользователя изменен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('READ')")
    @PostMapping("/pass/change")
    public ResponseEntity<?> changeUserPassword(@Valid @RequestBody UserChangePasswordRequest user) {
        try {
            userService.changePassword(user);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Request to user password change has been added successfully." +
                " Please check user email to confirm the change.",
                HttpStatus.OK);
    }

    @Operation(summary = "Изменение пользователя", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь изменен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('WRITE')")
    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest user) {
        user.setId(id);
        userService.update(user);
    }

    @Operation(summary = "Удаление пользователя", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь удален",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('WRITE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        UserResponse user = userService.findById(id);
        if (user.getRole().equals(Role.ADMIN_INACTIVE)
                || user.getRole().equals(Role.USER_INACTIVE)) {
            return new ResponseEntity<>("User already in inactive state.", HttpStatus.OK);
        }
        userService.deleteById(id);
        return new ResponseEntity<>("User set to inactive state successfully.", HttpStatus.OK);
    }

    @Operation(summary = "Сортировка списка пользователей по рейтингу",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список отсортирован",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/filter/stars")
    public List<UserResponse> findByStars(@RequestParam(defaultValue = "0", required = false) byte stars) {
        return userService.getByStars(stars);
    }

    @Operation(summary = "Сортировка списка пользователей по опыту работы",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список отсортирован",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/filter/exp={max}")
    public List<UserResponse> findByExperience(@PathVariable(name = "max") String max) { //если max то максимальный опыт. если sum, то общий опыт
        return userService.getByExperience(max);
    }
}
