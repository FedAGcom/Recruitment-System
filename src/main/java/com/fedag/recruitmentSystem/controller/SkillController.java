package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.dto.request.SkillRequest;
import com.fedag.recruitmentSystem.dto.request.SkillUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.SkillResponse;
import com.fedag.recruitmentSystem.service.impl.SkillServiceImpl;
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

  @Operation(summary = "Получение списка ключевых навыков", security = @SecurityRequirement(name = "bearerAuth"))
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Список загружен",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
      @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @PreAuthorize("hasAuthority('READ')")
  @GetMapping
  public Page<SkillResponse> getAllSkills(@PageableDefault(size = 5) Pageable pageable) {
    return skillService.getAllSkills(pageable);
  }

  @Operation(summary = "Получение ключевого навыка по id", security = @SecurityRequirement(name = "bearerAuth"))
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Навык найден",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
      @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @PreAuthorize("hasAuthority('READ')")
  @GetMapping("/{id}")
  public SkillResponse getById(@PathVariable Long id) {
    return skillService.findById(id);
  }

  @Operation(summary = "Удаление ключевого навыка", security = @SecurityRequirement(name = "bearerAuth"))
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Навык удален",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
      @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @PreAuthorize("hasAuthority('WRITE')")
  @DeleteMapping("/{id}")
  public void deleteVacancy(@PathVariable Long id) {
    skillService.deleteById(id);
  }

  @Operation(summary = "Добавление ключевого навыка", security = @SecurityRequirement(name = "bearerAuth"))
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Навык добавлен",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
      @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @PreAuthorize("hasAuthority('WRITE')")
  @PostMapping
  public void addVacancy(@RequestBody SkillRequest skillRequest) {
    skillService.save(skillRequest);
  }


  @Operation(summary = "Изменение ключевого навыка", security = @SecurityRequirement(name = "bearerAuth"))
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Навык изменен",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
      @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @PreAuthorize("hasAuthority('WRITE')")
  @PutMapping("/{id}")
  public void updateVacancy(@PathVariable Long id, @RequestBody SkillUpdateRequest skillUpdateRequest) {
    skillUpdateRequest.setId(id);
    skillService.update(skillUpdateRequest);
  }
}
