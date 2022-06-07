package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.dto.response.CompanyFeedbackResponse;
import com.fedag.recruitmentSystem.service.impl.CompanyFeedbackServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping(value = "/api/companies/feedbacks")
@Tag(name = "Контроллер отзывов о компаниях", description = "Работа с отзывами")
public class CompanyFeedbackController {

  @Schema(name = "Сервис отзывов", description = "Содержит имплементацию методов для работы с отзывами")
  private final CompanyFeedbackServiceImpl companyFeedBackService;

  @Operation(summary = "Получение списка отзывов")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Отзывы загружены",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @GetMapping
  public Page<CompanyFeedbackResponse> getAllCompanyFeedBack(Pageable pageable) {
    return companyFeedBackService.getAllCompanyFeedbacks(pageable);
  }

  @Operation(summary = "Получение отзыва по id")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Отзыв найден",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @GetMapping("/{id}")
  public CompanyFeedbackResponse getCompanyFeedBackById(@PathVariable Long id) {
    return companyFeedBackService.findById(id);
  }

  @Operation(summary = "Добавление отзыва")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Отзыв добавлен",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @PostMapping
  public void addCompanyFeedBack(@RequestBody CompanyFeedbackResponse companyFeedBack) {
    companyFeedBackService.save(companyFeedBack);
  }


  @Operation(summary = "Изменение отзыва")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Отзыв изменен",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @PutMapping
  public void updateVacancy(@RequestBody CompanyFeedbackResponse companyFeedBack) {
    companyFeedBackService.save(companyFeedBack);
  }

  @Operation(summary = "Удаление отзыва")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Отзыв удален",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @DeleteMapping("/{id}")
  public void deleteCompanyFeedBack(@PathVariable Long id) {
    companyFeedBackService.deleteById(id);
  }

}
