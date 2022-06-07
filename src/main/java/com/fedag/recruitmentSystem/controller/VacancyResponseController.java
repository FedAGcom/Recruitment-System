package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.dto.request.VacancyResponseRequest;
import com.fedag.recruitmentSystem.dto.request.VacancyResponseUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.VacancyResponseResponse;
import com.fedag.recruitmentSystem.service.impl.VacancyResponseServiceImpl;
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
@RequestMapping(value = "/api/vacancies/responses")
@Tag(name = "Контроллер откликов", description = "Работа с откликами")
public class VacancyResponseController {

  @Schema(name = "Сервис откликов", description = "Содержит имплементацию методов для работы с репозиторием")
  private final VacancyResponseServiceImpl vacancyResponseService;

  @Operation(summary = "Получение списка откликов")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Список загружен",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @GetMapping
  public Page<VacancyResponseResponse> getAllVacancyResponse(@PageableDefault(size = 5) Pageable pageable) {
    return vacancyResponseService.getAllVacanciesResponses(pageable);
  }

  @Operation(summary = "Получение отклика по id")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Отклик найден",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @GetMapping("/{id}")
  public VacancyResponseResponse getVacancyResponseById(@PathVariable Long id) {
    return vacancyResponseService.findById(id);
  }

  @Operation(summary = "Добавление отклика")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Отклик добавлен",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @PostMapping
  public void addVacancyResponse(@RequestBody VacancyResponseRequest vacancyResponse) {
    vacancyResponseService.save(vacancyResponse);
  }

  @Operation(summary = "Изменение отклика")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Отклик изменен",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @PutMapping("/{id}")
  public void updateVacancyResponse(@PathVariable Long id, @RequestBody VacancyResponseUpdateRequest vacancyResponse) {
    vacancyResponse.setId(id);
    vacancyResponseService.update(vacancyResponse);
  }

  @Operation(summary = "Удаление отклика")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Отклик удален",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @DeleteMapping("/{id}")
  public void addVacancyResponse(@PathVariable Long id) {
    vacancyResponseService.deleteById(id);
  }
}
