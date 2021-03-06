package com.fedag.recruitmentSystem.controller.admin_controller;

import com.fedag.recruitmentSystem.dto.request.ExperienceRequest;
import com.fedag.recruitmentSystem.dto.request.ExperienceUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.ExperienceResponseForAdmin;
import com.fedag.recruitmentSystem.enums.UrlConstants;
import com.fedag.recruitmentSystem.service.impl.ExperienceServiceImpl;
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

@RequiredArgsConstructor
@RestController
@RequestMapping(value = UrlConstants.MAIN_URL_ADMIN + UrlConstants.EXPERIENCE_URL)
@Tag(name = "Контроллер опыта работы для админа", description = "Работа с опытом работы")
public class ExperienceControllerForAdmin {

    @Schema(name = "Сервис опыта работы",
            description = "Содержит имплементацию методов для работы с репозиторием")
    private final ExperienceServiceImpl experienceService;

    @Operation(summary = "Получение списка мест работы", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список загружен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public Page<ExperienceResponseForAdmin> getAllResumes(@PageableDefault(size = 5) Pageable pageable) {
        return experienceService.getAllExperience(pageable);
    }

    @Operation(summary = "Получение места работы по id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Место работы найдено",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(UrlConstants.ID)
    public ExperienceResponseForAdmin getById(@PathVariable Long id) {
        return experienceService.findById(id);
    }

    @Operation(summary = "Добавление места работы", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Опыт работы добавлен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public void addExperience(@RequestBody ExperienceRequest experience) {
        experienceService.save(experience);
    }


    @Operation(summary = "Изменение места работы", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Опыт работы изменен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(UrlConstants.ID)
    public void updateExperience(@PathVariable Long id, @RequestBody ExperienceUpdateRequest experience) {
        experience.setId(id);
        experienceService.update(experience);
    }


    @Operation(summary = "Удаление опыта работы", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Опыт работы удален",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(UrlConstants.ID)
    public void deleteById(@PathVariable Long id) {
        experienceService.deleteById(id);
    }

    @Operation(summary = "Получение списка мест работы по id пользователя",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список загружен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/by_user_id" + UrlConstants.ID)
    public Page<ExperienceResponseForAdmin> getAllResumesByUserId(@PageableDefault(size = 5) Pageable pageable,
                                                                  @PathVariable Long id) {
        return experienceService.getAllExperienceByUserId(pageable, id);
    }
}
