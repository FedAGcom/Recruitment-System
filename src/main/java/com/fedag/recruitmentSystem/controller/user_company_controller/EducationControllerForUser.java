package com.fedag.recruitmentSystem.controller.user_company_controller;

import com.fedag.recruitmentSystem.dto.request.EducationRequest;
import com.fedag.recruitmentSystem.dto.request.EducationUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.EducationResponseForAdmin;
import com.fedag.recruitmentSystem.dto.response.user_response.EducationResponseForUser;
import com.fedag.recruitmentSystem.enums.UrlConstants;
import com.fedag.recruitmentSystem.service.impl.EducationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = UrlConstants.MAIN_URL_USER + UrlConstants.EDUCATION_URL)
@Tag(name = "Контроллер образования для пользователя", description = "Работа с образованием пользователя")
public class EducationControllerForUser {

    @Schema(name = "Сервис образования",
            description = "Содержит имплементацию методов для работы с репозиторием")
    private final EducationServiceImpl educationService;

    @Operation(summary = "Получение мест учёбы по id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Место учёбы найдено",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAnyAuthority('COMPANY','USER')")
    @GetMapping(UrlConstants.ID)
    public EducationResponseForUser getById(@PathVariable Long id) {
        return educationService.findByIdForUser(id);
    }

    @Operation(summary = "Добавление места учёбы", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Место добавлено",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping
    public void addEducation(@RequestBody EducationRequest educationRequest) {
        educationService.save(educationRequest);
    }

    @Operation(summary = "Изменение места учёбы", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Место учёбы изменено",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('USER')")
    @PutMapping(UrlConstants.ID)
    public void updateEducation(@PathVariable Long id, @RequestBody EducationUpdateRequest educationUpdateRequest) {
        educationUpdateRequest.setId(id);
        educationService.update(educationUpdateRequest);
    }

    @Operation(summary = "Удаление места учёбы", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Место учёбы удалено",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping(UrlConstants.ID)
    public void deleteEducation(@PathVariable Long id) {
        educationService.deleteById(id);
    }
}
