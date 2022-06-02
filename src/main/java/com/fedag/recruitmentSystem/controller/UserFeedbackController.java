package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.service.feedbackService.UserFeedbackService;
import com.fedag.recruitmentSystem.model.UserFeedback;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feedback")
@Tag(name = "Контроллер отзывов о пользователях", description = "Работа с отзывами")
public class UserFeedbackController {
    @Schema(name = "Сервис отзывов", description = "Содержит имплементацию методов для работы с репозиторием")
    private final UserFeedbackService userFeedbackService;
    @Operation(summary = "Получение списка отзывов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список загружен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @GetMapping
    public Page<UserFeedback> showAllFeedback(@PageableDefault(size = 5) Pageable pageable) {
        return userFeedbackService.findAllUserFeedback(pageable);
    }
    @Operation(summary = "Получение отзыва по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отзыв загружен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @GetMapping("/{id}")
    public UserFeedback getUserFeedback(@PathVariable Long id) {
        UserFeedback userFeedback = userFeedbackService.findUserFeedbackById(id);
        return userFeedback;
    }
    @Operation(summary = "Добавление отзыва")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Отзыв добавлен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PostMapping("/")
    public void addNewUserFeedback(@RequestBody UserFeedback userFeedback) {
        userFeedbackService.saveUserFeedback(userFeedback);
    }
    @Operation(summary = "Измение отзыва")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отзыв изменен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PutMapping("/")
    public void updateUserFeedback(@RequestBody UserFeedback userFeedback) {
        userFeedbackService.saveUserFeedback(userFeedback);
    }
    @Operation(summary = "Удаление отзыва")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отзыв удален",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @DeleteMapping("/{id}")
    public void deleteUserFeedback(@PathVariable Long id) {
        userFeedbackService.deleteUserFeedbackById(id);
    }
}
