package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.Resume;
import com.fedag.recruitmentSystem.service.impl.ResumeServiceImpl;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/resumes")
@Tag(name = "Контроллер резюме", description = "Работа с резюме")
public class ResumeController {

  @Schema(name = "Сервис резюме", description = "Содержит имплементацию методов для работы с репозиторием")
  private final ResumeServiceImpl resumeService;

  @Operation(summary = "Получение списка резюме")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Список загружен",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @GetMapping
  public Page<Resume> getAllResumes(@PageableDefault(size = 5) Pageable pageable) {
    return resumeService.getAllResumes(pageable);
  }

  @GetMapping("/search")
  public Page<Resume> getAllResumesByTextFilter(@RequestParam("text") String text,
                                                @PageableDefault(size = 15) Pageable pageable) {
    return resumeService.findByTextFilter(text, pageable);
  }

  @Operation(summary = "Получение резюме по id")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Резюме получено",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })

  @GetMapping("/{id}")
  public Resume getById(@PathVariable Long id) {
    return resumeService.findById(id);
  }

  @Operation(summary = "Добавление резюме")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Резюме добавлено",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @PostMapping
  public void addVacancy(@RequestBody Resume resume) {
    resumeService.save(resume);
  }

  @Operation(summary = "Изменение резюме")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Резюме изменено",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @PutMapping
  public void updateVacancy(@RequestBody Resume resume) {
    resumeService.save(resume);
  }

  @Operation(summary = "Удаление резюме")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Резюме удалено",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
    resumeService.deleteById(id);
  }
}
