package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.Experience;
import com.fedag.recruitmentSystem.service.impl.ExperienceServiceImpl;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/experiences")
@Tag(name = "Контроллер опыта работы", description = "Работа с опытом работы")
public class ExperienceController {

  @Schema(name = "Сервис опыта работы", description = "Содержит имплементацию методов для работы с репозиторием")
  private final ExperienceServiceImpl experienceService;

  @Operation(summary = "Получение списка мест работы")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Список загружен",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @GetMapping
  public Page<Experience> getAllResumes(@PageableDefault(size = 5) Pageable pageable) {
    return experienceService.getAllExperience(pageable);
  }

  @Operation(summary = "Получение места работы по id")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Место работы найдено",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @GetMapping("/{id}")
  public Experience getById(@PathVariable Long id) {
    return experienceService.findById(id);
  }

  @Operation(summary = "Получение списка мест работы")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Опыт работы добавлен",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @PostMapping
  public void addVacancy(@RequestBody Experience experience) {
    experienceService.save(experience);
  }

  @Operation(summary = "Изменение опыта работы")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Опыт работы изменен",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @PutMapping
  public void updateVacancy(@RequestBody Experience experience) {
    experienceService.save(experience);
  }

  @Operation(summary = "Удаление опыта работы")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Опыт работы удален",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
    experienceService.deleteById(id);
  }
}
