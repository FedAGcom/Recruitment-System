package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.dto.request.ExamRequest;
import com.fedag.recruitmentSystem.dto.request.ExamUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.ExamResponse;
import com.fedag.recruitmentSystem.service.impl.ExamServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
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
@RequestMapping("/api/exams")
@Tag(name = "Контроллер тестов", description = "Работа с тестами")
public class ExamController {

  private final ExamServiceImpl examService;

  @Operation(summary = "Получение списка тестов")
  @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Тесты загружены",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
  @GetMapping
  public Page<ExamResponse> showAllExams(@PageableDefault(size = 5) Pageable pageable) {
    return examService.getAllExams(pageable);
  }
  @Operation(summary = "Получение теста по id")
  @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Тест найден",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @GetMapping("/{id}")
  public ExamResponse getExam(@PathVariable Long id) {
    return examService.findById(id);
  }

    @Operation(summary = "Добавление теста")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Тест добавлен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
  @PostMapping
  public void addNewExam(@RequestBody ExamRequest exam) {
    examService.save(exam);
  }

  @Operation(summary = "Изменение теста")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Тест изменен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
  @PutMapping("/{id}")
  public void updateExam(@PathVariable Long id, @RequestBody ExamUpdateRequest exam) {
    exam.setId(id);
    examService.update(exam);
  }

    @Operation(summary = "Удаление теста")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Тест удален",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
  @DeleteMapping("/{id}")
  public void deleteExam(@PathVariable Long id) {
    examService.deleteById(id);
  }
}
