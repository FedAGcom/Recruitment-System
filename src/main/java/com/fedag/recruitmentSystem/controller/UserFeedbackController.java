package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.dto.request.UserFeedbackRequest;
import com.fedag.recruitmentSystem.dto.response.UserFeedbackResponse;
import com.fedag.recruitmentSystem.service.impl.UserFeedbackServiceImpl;
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
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/feedbacks")
@Tag(name = "Контроллер отзывов о пользователях", description = "Работа с отзывами")
public class UserFeedbackController {

    @Schema(name = "Сервис отзывов", description = "Содержит имплементацию методов для работы с репозиторием")
    private final UserFeedbackServiceImpl userFeedbackService;

    @Operation(summary = "Получение списка отзывов", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список загружен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('READ')")
    @GetMapping
    public Page<UserFeedbackResponse> showAllFeedback(@PageableDefault(size = 5) Pageable pageable) {
        return userFeedbackService.getAllUserFeedbacks(pageable);
    }

    @Operation(summary = "Получение отзыва по id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отзыв загружен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/{id}")
    public UserFeedbackResponse getUserFeedback(@PathVariable Long id) {
        return userFeedbackService.findById(id);
    }

    @Operation(summary = "Добавление отзыва", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Отзыв добавлен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('WRITE')")
    @PostMapping
    public void addNewUserFeedback(@RequestBody UserFeedbackRequest userFeedback) {
        userFeedbackService.save(userFeedback);
    }

    @Operation(summary = "Измение отзыва", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отзыв изменен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('WRITE')")
    @PutMapping
    public void updateUserFeedback(@RequestBody UserFeedbackRequest userFeedback) {
        userFeedbackService.save(userFeedback);
    }

    @Operation(summary = "Удаление отзыва", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отзыв удален",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('WRITE')")
    @DeleteMapping("/{id}")
    public void deleteUserFeedback(@PathVariable Long id) {
        userFeedbackService.deleteById(id);
    }
}
