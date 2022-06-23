package com.fedag.recruitmentSystem.controller.user_company_controller;

import com.fedag.recruitmentSystem.dto.request.UserChangePasswordRequest;
import com.fedag.recruitmentSystem.dto.response.UserResponseForAdmin;
import com.fedag.recruitmentSystem.dto.response.user_response.UserResponseForUser;
import com.fedag.recruitmentSystem.enums.UrlConstants;
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
@RequestMapping(value = UrlConstants.MAIN_URL_USER + UrlConstants.USER_URL)
@Tag(name = "Контроллер пользователей для пользователей", description = "Работа с пользователями")
public class UserControllerForUser {

    @Schema(name = "Сервис пользователей", description = "Содержит имплементацию методов для работы с репозиторием")
    private final UserServiceImpl userService;

    @Operation(summary = "Получение списка пользователей", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список загружен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAnyAuthority('COMPANY','USER')")
    @GetMapping
    public Page<UserResponseForUser> getAllUsers(@PageableDefault(size = 5) Pageable pageable) {
        return userService.getAllUsersForUser(pageable);
    }

    @Operation(summary = "Сортировка списка пользователей по вступительным экзаменам",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список отсортирован",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('COMPANY')")
    @GetMapping("/filter")
    public List<UserResponseForUser> findByEntranceExamScore(@RequestParam(defaultValue = "0",
            required = false) int score) {
        return userService.getByEntranceExamScoreForUser(score);
    }

    @Operation(summary = "Получение пользователя по id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь получен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAnyAuthority('COMPANY','USER')")
    @GetMapping(UrlConstants.ID)
    public UserResponseForUser getUser(@PathVariable Long id) {
        return userService.findByIdForUser(id);
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
    @PreAuthorize("hasAuthority('USER')")
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

    @Operation(summary = "Сортировка списка пользователей по рейтингу",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список отсортирован",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('COMPANY')")
    @GetMapping("/filter/stars")
    public List<UserResponseForUser> findByStars(@RequestParam(defaultValue = "0", required = false) byte stars) {
        return userService.getByStarsForUser(stars);
    }

    @Operation(summary = "Сортировка списка пользователей по опыту работы",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список отсортирован",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('COMPANY')")
    @GetMapping("/filter/exp={max}")
    public List<UserResponseForUser> findByExperience(@PathVariable(name = "max") String max) { //если max то максимальный опыт. если sum, то общий опыт
        return userService.getByExperienceForUser(max);
    }
}
