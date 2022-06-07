package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.dto.UserFeedbackRequest;
import com.fedag.recruitmentSystem.dto.UserFeedbackResponse;
import com.fedag.recruitmentSystem.model.UserFeedback;
import com.fedag.recruitmentSystem.service.impl.UserFeedbackServiceImpl;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/feedbacks")
@Tag(name = "Контроллер отзывов о пользователях", description = "Работа с отзывами")
public class UserFeedbackController {

  @Schema(name = "Сервис отзывов", description = "Содержит имплементацию методов для работы с репозиторием")
  private final UserFeedbackServiceImpl userFeedbackService;

  @Operation(summary = "Получение списка отзывов")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Список загружен",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @GetMapping
  public Page<UserFeedbackResponse> showAllFeedback(@PageableDefault(size = 5) Pageable pageable) {
    return userFeedbackService.getAllUserFeedbacks(pageable);
  }

  @Operation(summary = "Получение отзыва по id")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Отзыв загружен",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @GetMapping("/{id}")
  public UserFeedbackResponse getUserFeedback(@PathVariable Long id) {
    return userFeedbackService.findById(id);
  }

  @Operation(summary = "Добавление отзыва")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Отзыв добавлен",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @PostMapping
  public void addNewUserFeedback(@RequestBody UserFeedbackRequest userFeedback) {
    userFeedbackService.save(userFeedback);
  }

  @Operation(summary = "Измение отзыва")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Отзыв изменен",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @PutMapping
  public void updateUserFeedback(@RequestBody UserFeedbackRequest userFeedback) {
    userFeedbackService.save(userFeedback);
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
    userFeedbackService.deleteById(id);
  }
}
