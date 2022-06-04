package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.Vacancy;
import com.fedag.recruitmentSystem.service.impl.VacancyServiceImpl;
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
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.MediaType;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/vacancies")
@Tag(name = "Контроллер вакансий", description = "Работа с вакансиями")
public class VacancyController {
  @Schema(name = "Сервис вакансий", description = "Содержит имплементацию методов для работы с репозиторием")
  private final VacancyServiceImpl vacancyService;

  @Operation(summary = "Получение списка вакансий")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Список загружен",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @GetMapping
  public Page<Vacancy> getAllVacancies(@PageableDefault(size = 5) Pageable pageable) {
    return vacancyService.getAllVacancies(pageable);
  }

  @Operation(summary = "Получение вакансии по id")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Вакансий найдена",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @GetMapping("/{id}")
  public Vacancy getById(@PathVariable Long id) {
    return vacancyService.findById(id);
  }

  @Operation(summary = "Удаление вакансии")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Вакансия удалена",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @DeleteMapping("/{id}")
  public void deleteVacancy(@PathVariable Long id) {
    vacancyService.deleteById(id);
  }

  @Operation(summary = "Добавление вакансии")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Вакансия добавлена",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @PostMapping("/add")
  public void addVacancy(@RequestBody Vacancy vacancy) {
    vacancyService.save(vacancy);
  }

  @Operation(summary = "Изменение вакансии")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Вакансия изменена",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @PutMapping("/{id}")
  public void updateVacancy(@PathVariable Long id, @RequestBody Vacancy vacancy) {
    vacancyService.save(vacancy);
  }

  @GetMapping("/filter/date")
  public List<Vacancy> findByDateCreated(@RequestParam(defaultValue = "0", required = false) LocalDateTime dateCreated) {
    return vacancyService.findByDateCreated(dateCreated);
  }
}
