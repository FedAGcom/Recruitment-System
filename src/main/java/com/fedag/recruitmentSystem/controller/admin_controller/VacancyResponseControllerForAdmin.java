package com.fedag.recruitmentSystem.controller.admin_controller;

import com.fedag.recruitmentSystem.dto.request.VacancyResponseRequest;
import com.fedag.recruitmentSystem.dto.request.VacancyResponseUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.VacancyResponseResponseForAdmin;
import com.fedag.recruitmentSystem.enums.UrlConstants;
import com.fedag.recruitmentSystem.service.impl.VacancyResponseServiceImpl;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = UrlConstants.MAIN_URL_ADMIN + UrlConstants.VACANCY_RESPONSE_URL)
@Tag(name = "Контроллер откликов для админа", description = "Работа с откликами")
public class VacancyResponseControllerForAdmin {

    @Schema(name = "Сервис откликов", description = "Содержит имплементацию методов для работы с репозиторием")
    private final VacancyResponseServiceImpl vacancyResponseService;

    @Operation(summary = "Получение списка откликов", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список загружен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public Page<VacancyResponseResponseForAdmin> getAllVacancyResponse(
            @PageableDefault(size = 5) Pageable pageable) {
        return vacancyResponseService.getAllVacanciesResponses(pageable);
    }

    @Operation(summary = "Получение отклика по id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отклик найден",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(UrlConstants.ID)
    public VacancyResponseResponseForAdmin getVacancyResponseById(@PathVariable Long id) {
        return vacancyResponseService.findById(id);
    }

    @Operation(summary = "Добавление отклика", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Отклик добавлен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public void addVacancyResponse(@RequestBody VacancyResponseRequest vacancyResponse) {
        vacancyResponseService.save(vacancyResponse);
    }

    @Operation(summary = "Изменение отклика", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отклик изменен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(UrlConstants.ID)
    public void updateVacancyResponse(@PathVariable Long id,
                                      @RequestBody VacancyResponseUpdateRequest vacancyResponse) {
        vacancyResponse.setId(id);
        vacancyResponseService.update(vacancyResponse);
    }

    @Operation(summary = "Удаление отклика", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отклик удален",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(UrlConstants.ID)
    public void addVacancyResponse(@PathVariable Long id) {
        vacancyResponseService.deleteById(id);
    }

    @Operation(summary = "Получение списка откликов определенного пользователя",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список загружен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/by_user_id" + UrlConstants.ID)
    public Page<VacancyResponseResponseForAdmin> getAllVacancyResponseByUserId(
            @PageableDefault(size = 5) Pageable pageable, @PathVariable Long id) {
        return vacancyResponseService.getAllVacanciesResponsesByUserId(pageable, id);
    }
}
