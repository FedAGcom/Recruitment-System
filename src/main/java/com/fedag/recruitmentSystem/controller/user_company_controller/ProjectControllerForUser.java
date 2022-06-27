package com.fedag.recruitmentSystem.controller.user_company_controller;

import com.fedag.recruitmentSystem.dto.response.user_response.ProjectResponseForUser;
import com.fedag.recruitmentSystem.enums.UrlConstants;
import com.fedag.recruitmentSystem.service.impl.ProjectServiceImpl;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = UrlConstants.MAIN_URL_USER + UrlConstants.PROJECT_URL)
@Tag(name = "Контроллер проектов для пользователя", description = "Работа с проектами")
public class ProjectControllerForUser {

    @Schema(name = "Сервис проектов",
            description = "Содержит имплементацию методов для работы с репозиторием")
    private final ProjectServiceImpl projectService;

    @Operation(summary = "Получение списка проектов", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список загружен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAnyAuthority('COMPANY','USER')")
    @GetMapping
    public Page<ProjectResponseForUser> getAllProjects(@PageableDefault(size = 5) Pageable pageable) {
        return projectService.getAllProjectsForUser(pageable);
    }

    @Operation(summary = "Получение проекта по id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь получен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAnyAuthority('COMPANY','USER')")
    @GetMapping(UrlConstants.ID)
    public ProjectResponseForUser getProject(@PathVariable Long id) {
        return projectService.findByIdForUser(id);
    }

}
