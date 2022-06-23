package com.fedag.recruitmentSystem.controller.admin_controller;

import com.fedag.recruitmentSystem.enums.UrlConstants;
import com.fedag.recruitmentSystem.model.Question;
import com.fedag.recruitmentSystem.service.impl.QuestionServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = UrlConstants.MAIN_URL_ADMIN + UrlConstants.QUESTION_URL)
@Tag(name = "Контроллер вопросов для админа", description = "Все CRUD операции с вопросами")
public class QuestionControllerForAdmin {

    @Schema(name = "Сервис вопросов", description = "Содержит реализацию методов для работы с репозиторием")
    private final QuestionServiceImpl questionService;

    @Operation(summary = "Добавление вопроса", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Вопрос добавлен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('ADMIN')") // поменять статус на WRITE в релизе
    @PostMapping
    public String addQuestion(@RequestParam("title") String title, @RequestParam("question") String question,
                              @RequestParam("answer") String answer, @RequestParam("correct") String correct) {
        String id = UUID.randomUUID().toString();
        questionService.addQuestion(id, title, question, answer, correct);
        return id;
    }

    @Operation(summary = "Получение списка вопросов по заголовку", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Вопрос получен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('ADMIN')") // поменять статус на WRITE в релизе
    @GetMapping
    public List<Question> search(@RequestParam("query") String query) {
        return questionService.searchQuestionsByTitle(query);
    }

    @Operation(summary = "Удаление вопроса по ID", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Вопрос удалён",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('ADMIN')") // поменять статус на WRITE в релизе
    @DeleteMapping
    public void deleteById(@RequestParam("id") String id) {
        questionService.deleteQuestionById(id);
    }

}
