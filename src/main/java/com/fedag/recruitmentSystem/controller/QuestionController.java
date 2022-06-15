package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.dto.request.QuestionRequest;
import com.fedag.recruitmentSystem.dto.request.QuestionUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.QuestionResponse;
import com.fedag.recruitmentSystem.service.impl.QuestionServiceImpl;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/questions")
@Tag(name = "Контроллер вопросов", description = "Все CRUD операции с вопросами")
public class QuestionController {

    @Schema(name = "Сервис вопросов", description = "Содержит реализацию методов для работы с репозиторием")
    private final QuestionServiceImpl questionService;

    @Operation(summary = "Получение вопроса по id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Вопрос получено",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/{id}")
    public QuestionResponse getById(@PathVariable String id) {
        return questionService.findById(id);
    }

    @Operation(summary = "Добавление вопроса", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Вопрос добавлен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('WRITE')")
    @PostMapping
    public void addQuestion(@RequestBody QuestionRequest question) {
        questionService.save(question);
    }

    @Operation(summary = "Изменение вопроса", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Вопрос изменён",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('WRITE')")
    @PutMapping("/{id}")
    public void updateQuestion(@PathVariable String id, @RequestBody QuestionUpdateRequest question) {
        question.setId(id);
        questionService.update(question);
    }

    @Operation(summary = "Удаление вопроса", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Вопрос удалён",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('WRITE')")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        questionService.deleteById(id);
    }

    @Operation(summary = "Получение списка вопросов", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список вопросов загружен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('READ')")
    @GetMapping
    public Page<QuestionResponse> getAllQuestions(@PageableDefault(size = 5) Pageable pageable) {
        return questionService.getAllQuestions(pageable);
    }
}
