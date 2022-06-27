package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.dto.request.TestResultRequest;
import com.fedag.recruitmentSystem.dto.request.TestResultUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.TestResultResponse;
import com.fedag.recruitmentSystem.service.impl.TestResultServiceImpl;
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
@RequestMapping(value = "/api/test/results")
@Tag(name = "Контроллер результатов тестирования", description = "Работа с оценкой за тест")
public class TestResultController {

    @Schema(name = "Сервис тестирования", description = "Содержит имплементацию методов для работы с репозиторием")
    private final TestResultServiceImpl testService;

    @Operation(summary = "Получение списка результатов тестирования", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список загружен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('READ')")
    @GetMapping
    public Page<TestResultResponse> getAllTestResults(@PageableDefault(size = 5) Pageable pageable) {
        return testService.getAllTest(pageable);
    }

    @Operation(summary = "Получение результата теста по id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Результат получен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/{id}")
    public TestResultResponse getTest(@PathVariable Long id) {
        return testService.findById(id);
    }

    @Operation(summary = "Добавление результата за тест", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Результат добавлен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('READ')")
    @PostMapping
    public void addNewTest(@RequestBody TestResultRequest test) {
        testService.save(test);
    }

    @Operation(summary = "Изменение результата тестирования", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Результат изменен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('READ')")
    @PutMapping("/{id}")
    public void updateTest(@PathVariable Long id, @RequestBody TestResultUpdateRequest test) {
        test.setId(id);
        testService.update(test);
    }

    @Operation(summary = "Удаление результата тестирования", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Тест удален",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('READ')")
    @DeleteMapping("/{id}")
    public void deleteTest(@PathVariable Long id) {
        testService.deleteById(id);
    }
}
