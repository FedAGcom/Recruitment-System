package com.fedag.recruitmentSystem.controller.user_company_controller;

import com.fedag.recruitmentSystem.dto.request.VacancyResponseRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.VacancyResponseResponseForAdmin;
import com.fedag.recruitmentSystem.dto.response.user_response.VacancyResponseResponseForUser;
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
@RequestMapping(value = UrlConstants.MAIN_URL_USER + UrlConstants.VACANCY_RESPONSE_URL)
@Tag(name = "Контроллер откликов для пользователя", description = "Работа с откликами")
public class VacancyResponseControllerForUser {

    @Schema(name = "Сервис откликов", description = "Содержит имплементацию методов для работы с репозиторием")
    private final VacancyResponseServiceImpl vacancyResponseService;

    @Operation(summary = "Получение отклика по id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отклик найден",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAnyAuthority('COMPANY','USER')")
    @GetMapping(UrlConstants.ID)
    public VacancyResponseResponseForUser getVacancyResponseById(@PathVariable Long id) {
        return vacancyResponseService.findByIdForUser(id);
    }

    @Operation(summary = "Добавление отклика", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Отклик добавлен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping
    public void addVacancyResponse(@RequestBody VacancyResponseRequest vacancyResponse) {
        vacancyResponseService.save(vacancyResponse);
    }

    @Operation(summary = "Удаление отклика", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отклик удален",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping(UrlConstants.ID)
    public void deleteVacancyResponse(@PathVariable Long id) {
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
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/by_user_id" + UrlConstants.ID)
    public Page<VacancyResponseResponseForUser> getAllVacancyResponseByUserId(@PageableDefault(size = 5) Pageable pageable,
                                                                               @PathVariable Long id) {
        return vacancyResponseService.getAllVacanciesResponsesByUserIdForUser(pageable, id);
    }
}
