package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.dto.request.ExamRequest;
import com.fedag.recruitmentSystem.dto.response.ExamResponse;
import com.fedag.recruitmentSystem.service.impl.ExamServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
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
@RequestMapping("/api/exams")
@Tag(name = "Контроллер тестов", description = "Работа с тестами")
public class ExamController {

    private final ExamServiceImpl examService;

    @Operation(summary = "Получение списка тестов", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Тесты загружены",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('READ')")
    @GetMapping
    public Page<ExamResponse> showAllExams(@PageableDefault(size = 5) Pageable pageable) {
        return examService.getAllExams(pageable);
    }

    @Operation(summary = "Получение теста по id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Тест найден",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/{id}")
    public ExamResponse getExam(@PathVariable Long id) {
        return examService.findById(id);
    }

    @Operation(summary = "Добавление теста", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Тест добавлен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('WRITE')")
    @PostMapping
    public void addNewExam(@RequestBody ExamRequest exam) {
        examService.save(exam);
    }

    @Operation(summary = "Изменение теста", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Тест изменен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('WRITE')")
    @PutMapping
    public void updateExam(@RequestBody ExamRequest exam) {
        examService.save(exam);
    }

    @Operation(summary = "Удаление теста", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Тест удален",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('WRITE')")
    @DeleteMapping("/{id}")
    public void deleteExam(@PathVariable Long id) {
        examService.deleteById(id);
    }
}
