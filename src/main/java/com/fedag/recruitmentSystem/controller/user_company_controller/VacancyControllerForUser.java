package com.fedag.recruitmentSystem.controller.user_company_controller;

import com.fedag.recruitmentSystem.dto.request.VacancyRequest;
import com.fedag.recruitmentSystem.dto.request.VacancyUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.user_response.VacancyResponseForUser;
import com.fedag.recruitmentSystem.enums.UrlConstants;
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
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = UrlConstants.MAIN_URL_USER + UrlConstants.VACANCY_URL)
@Tag(name = "Контроллер вакансий для пользователей", description = "Работа с вакансиями")
public class VacancyControllerForUser {

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
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping
    public Page<VacancyResponseForUser> getAllVacancies(@PageableDefault(size = 5) Pageable pageable) {
        return vacancyService.getAllVacanciesForUser(pageable);
    }

    @Operation(summary = "Получение вакансии по id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Вакансий найдена",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAnyAuthority('COMPANY','USER')")
    @GetMapping(UrlConstants.ID)
    public VacancyResponseForUser getById(@PathVariable Long id) {
        return vacancyService.findByIdForUser(id);
    }

    @Operation(summary = "Удаление вакансии", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Вакансия удалена",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('COMPANY')")
    @DeleteMapping(UrlConstants.ID)
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
    @PreAuthorize("hasAuthority('COMPANY')")
    @PostMapping
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
    @PreAuthorize("hasAuthority('COMPANY')")
    @PutMapping(UrlConstants.ID)
    public void updateVacancy(@PathVariable Long id, @RequestBody VacancyUpdateRequest vacancyUpdateRequest) {
        vacancyUpdateRequest.setId(id);
        vacancyService.update(vacancyUpdateRequest);
    }

    @Operation(summary = "Фильтр вакансий по дате", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Вакансия изменена",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/filter/date")
    public List<VacancyResponseForUser> findByDateCreated() {
        return vacancyService.findByDateCreatedForUser();
    }

    /**
     * @description Google Calendar API methods below:
     */
    @SneakyThrows
    @Operation(summary = "Получение всех собеседований в календаре",
            security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAnyAuthority('COMPANY','USER')")
    @GetMapping(UrlConstants.ID + "/events")
    public List<String> findCalendarEvents(@PathVariable String id) {
        VacancyResponseForUser vacancy = vacancyService.findByIdForUser(Long.parseLong(id));
        String companyCalendarId = companyService.findById(vacancy.getCompanyId()).getCalendarId();
        return vacancyService.findCalendarEvents(companyCalendarId);
    }

    @SneakyThrows
    @Operation(summary = "Создание собеседования в календаре",
            security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAuthority('COMPANY')")
    @PostMapping(UrlConstants.ID + "/events")
    public void createCalendarEvents(
            @PathVariable String id,
            @RequestBody CustomCalendarEvent customCalendarEvent) {

        VacancyResponseForUser vacancy = vacancyService.findByIdForUser(Long.parseLong(id));
        String companyCalendarId = companyService.findById(vacancy.getCompanyId()).getCalendarId();

        vacancyService.createCalendarEvent(companyCalendarId, customCalendarEvent);
    }

    @Operation(summary = "Получение списка вакансий по id компании",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список загружен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/by_user_id" + UrlConstants.ID)
    public Page<VacancyResponseForUser> getAllVacanciesByUserId(@PageableDefault(size = 5) Pageable pageable,
                                                                @PathVariable Long id) {
        return vacancyService.getAllVacanciesByCompanyIdForUser(pageable, id);
    }
}