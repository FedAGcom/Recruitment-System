package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.dto.request.CompanyRequest;
import com.fedag.recruitmentSystem.dto.request.CompanyUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.CompanyResponse;
import com.fedag.recruitmentSystem.service.impl.CompanyServiceImpl;
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
@RequestMapping(value = "/api/companies")
@Tag(name = "Контроллер компаний", description = "Работа с компаниями")
public class CompanyController {

  @Schema(name = "Сервис компаний", description = "Содержит имплементацию методов для работы с репозиторием")
  private final CompanyServiceImpl companyService;

  @Operation(summary = "Получение списка компаний")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Компании загружены",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @GetMapping
  public Page<CompanyResponse> getAllCompanies(@PageableDefault(size = 5) Pageable pageable) {
    return companyService.getAllCompanies(pageable);
  }

  @Operation(summary = "Получение компании по id")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Компания найдена",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @GetMapping("/{id}")
  public CompanyResponse getById(@PathVariable Long id) {
    return companyService.findById(id);
  }

  @Operation(summary = "Удаление кампании")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Компания загружены",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @DeleteMapping("/{id}")
  public void deleteVacancy(@PathVariable Long id) {
    companyService.deleteById(id);
  }

  @Operation(summary = "Добавление компании")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "Компания добавлена",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @PostMapping
  public void addVacancy(@RequestBody CompanyRequest companyRequest) {
    companyService.save(companyRequest);
  }

  @Operation(summary = "Изменение компании")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Компания изменена",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
          @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                  content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @PutMapping("/{id}")
  public void updateVacancy(@PathVariable Long id, @RequestBody CompanyUpdateRequest companyUpdateRequest) {
    companyService.update(companyUpdateRequest);
  }
}
