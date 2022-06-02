package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.VacancyResponse;
import com.fedag.recruitmentSystem.service.VacancyResponseService;
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
@RequestMapping(value = "/api/vacancy/response")
@Tag(name = "Контроллер откликов", description = "Работа с откликами")
public class VacancyResponseController {

    @Schema(name = "Сервис откликов", description = "Содержит имплементацию методов для работы с репозиторием")
    private final VacancyResponseService vacancyResponseService;
    @Operation(summary = "Получение списка откликов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список загружен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @GetMapping
    public Page<VacancyResponse> getAllVacancyResponse(@PageableDefault(size = 5) Pageable pageable) {
        return vacancyResponseService.getAllVacancyResponse(pageable);
    }

    @Operation(summary = "Получение отклика по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отклик найден",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @GetMapping("/{id}")
    public VacancyResponse getVacancyResponseById(@PathVariable Long id) {
        return vacancyResponseService.getVacancyResponseById(id);
    }

    @Operation(summary = "Добавление отклика")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Отклик добавлен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PostMapping("/add")
    public VacancyResponse addVacancyResponse(@RequestBody VacancyResponse vacancyResponse) {
        return vacancyResponseService.addVacancyResponse(vacancyResponse);
    }

    @Operation(summary = "Удаление отклика")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отклик удален",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @DeleteMapping("/{id}")
    public void deleteVacancyResponse(@PathVariable Long id) {
        vacancyResponseService.removeVacancyResponse(id);
    }

}
