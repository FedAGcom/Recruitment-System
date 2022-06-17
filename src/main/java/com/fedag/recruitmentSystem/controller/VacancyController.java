package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.dto.request.VacancyRequest;
import com.fedag.recruitmentSystem.dto.request.VacancyUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.VacancyResponse;
import com.fedag.recruitmentSystem.model.CustomCalendarEvent;
import com.fedag.recruitmentSystem.service.impl.CompanyServiceImpl;
import com.fedag.recruitmentSystem.service.impl.VacancyServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/vacancies")
@Tag(name = "Контроллер вакансий", description = "Работа с вакансиями")
public class VacancyController {

  @Schema(name = "Сервис вакансий", description = "Содержит имплементацию методов для работы с репозиторием")
  private final VacancyServiceImpl vacancyService;
  private final CompanyServiceImpl companyService;

  @Operation(summary = "Получение списка вакансий", security = @SecurityRequirement(name = "bearerAuth"))
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Список загружен",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
      @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @PreAuthorize("hasAuthority('READ')")
  @GetMapping
  public Page<VacancyResponse> getAllVacancies(@PageableDefault(size = 5) Pageable pageable) {
    return vacancyService.getAllVacancies(pageable);
  }

  @Operation(summary = "Получение вакансии по id", security = @SecurityRequirement(name = "bearerAuth"))
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Вакансий найдена",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
      @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @PreAuthorize("hasAuthority('READ')")
  @GetMapping("/{id}")
  public VacancyResponse getById(@PathVariable Long id) {
    return vacancyService.findById(id);
  }

  @Operation(summary = "Удаление вакансии", security = @SecurityRequirement(name = "bearerAuth"))
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Вакансия удалена",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
      @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @PreAuthorize("hasAuthority('WRITE')")
  @DeleteMapping("/{id}")
  public void deleteVacancy(@PathVariable Long id) {
    vacancyService.deleteById(id);
  }

  @Operation(summary = "Добавление вакансии", security = @SecurityRequirement(name = "bearerAuth"))
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Вакансия добавлена",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
      @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @PreAuthorize("hasAuthority('WRITE')")
  @PostMapping("/add")
  public void addVacancy(@RequestBody VacancyRequest vacancyRequest) {
    vacancyService.save(vacancyRequest);
  }

  @Operation(summary = "Изменение вакансии", security = @SecurityRequirement(name = "bearerAuth"))
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Вакансия изменена",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
      @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
          content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
  })
  @PreAuthorize("hasAuthority('WRITE')")
  @PutMapping("/{id}")
  public void updateVacancy(@PathVariable Long id, @RequestBody VacancyUpdateRequest vacancyUpdateRequest) {
    vacancyUpdateRequest.setId(id);
    vacancyService.update(vacancyUpdateRequest);
  }

  @Operation(summary = "Фильтр вакансий по дате", security = @SecurityRequirement(name = "bearerAuth"))
  @PreAuthorize("hasAuthority('READ')")
  @GetMapping("/filter/date")
  public List<VacancyResponse> findByDateCreated() {
    return vacancyService.findByDateCreated();
  }

  /**
   * @description Google Calendar API methods below:
   */
  @SneakyThrows
  @Operation(summary = "Получение всех собеседований в календаре", security = @SecurityRequirement(name = "bearerAuth"))
  @PreAuthorize("hasAuthority('READ')")
  @GetMapping("/{id}/events")
  public List<String> findCalendarEvents(@PathVariable String id) {
    VacancyResponse vacancy = vacancyService.findById(Long.parseLong(id));
    String companyCalendarId = companyService.findById(vacancy.getCompanyId()).getCalendarId();
    return vacancyService.findCalendarEvents(companyCalendarId);
  }

  @SneakyThrows
  @Operation(summary = "Создание собеседования в календаре", security = @SecurityRequirement(name = "bearerAuth"))
  @PreAuthorize("hasAuthority('READ')")
  @PostMapping("/{id}/events")
  public void createCalendarEvents(
      @PathVariable String id,
      @RequestBody CustomCalendarEvent customCalendarEvent) {

    VacancyResponse vacancy = vacancyService.findById(Long.parseLong(id));
    String companyCalendarId = companyService.findById(vacancy.getCompanyId()).getCalendarId();

    vacancyService.createCalendarEvent(companyCalendarId, customCalendarEvent);
  }

  @SneakyThrows
  @Operation(summary = "Запись на собеседование в календаре", security = @SecurityRequirement(name = "bearerAuth"))
  @PreAuthorize("hasAuthority('READ')")
  @GetMapping("/{vacancyId}/events/signup")
  public void signForEvent(
      @PathVariable String vacancyId,
      @RequestParam String eventSummary) {

    VacancyResponse vacancy = vacancyService.findById(Long.parseLong(vacancyId));
    String companyCalendarId = companyService.findById(vacancy.getCompanyId()).getCalendarId();

    vacancyService.signForEvent(companyCalendarId, eventSummary);
  }
}