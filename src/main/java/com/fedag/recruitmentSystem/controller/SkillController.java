package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.domain.dto.SkillDto;
import com.fedag.recruitmentSystem.domain.entity.Skill;
import com.fedag.recruitmentSystem.service.impl.SkillServiceImpl;
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
@RequestMapping(value = "/api/skills")
@Tag(name = "Контроллер ключевых навыков", description = "Работа с ключевыми навыками")
public class SkillController {

  @Schema(name = "Сервис ключевых навыков", description = "Содержит имплементацию методов для работы с репозиторием")
  private final SkillServiceImpl skillService;

  @Operation(summary = "Получение списка ключевых навыков")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Список загружен",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @GetMapping
  public Page<SkillDto> getAllSkills(@PageableDefault(size = 5) Pageable pageable) {
    return skillService.getAllSkills(pageable);
  }

  @Operation(summary = "Получение ключевого навыка по id")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Навык найден",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @GetMapping("/{id}")
  public SkillDto getById(@PathVariable Long id) {
    return skillService.findById(id);
  }

  @Operation(summary = "Удаление ключевого навыка")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Навык удален",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @DeleteMapping("/{id}")
  public void deleteVacancy(@PathVariable Long id) {
    skillService.deleteById(id);
  }

  @Operation(summary = "Добавление ключевого навыка")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Навык добавлен",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @PostMapping
  public void addVacancy(@RequestBody SkillDto skillDto) {
    skillService.save(skillDto);
  }

  @Operation(summary = "Изменение ключевого навыка")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Навык изменен",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @PutMapping("/{id}")
  public void updateVacancy(@PathVariable Long id, @RequestBody SkillDto skillDto) {
    skillService.save(skillDto);
  }
}
