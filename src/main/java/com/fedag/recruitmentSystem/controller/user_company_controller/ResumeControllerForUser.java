package com.fedag.recruitmentSystem.controller.user_company_controller;

import com.fedag.recruitmentSystem.dto.request.ResumeRequest;
import com.fedag.recruitmentSystem.dto.request.ResumeUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.user_response.ExperienceResponseForUser;
import com.fedag.recruitmentSystem.dto.response.user_response.ResumeResponseForUser;
import com.fedag.recruitmentSystem.enums.UrlConstants;
import com.fedag.recruitmentSystem.service.impl.ResumeServiceImpl;
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

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = UrlConstants.MAIN_URL_USER + UrlConstants.RESUME_URL)
@Tag(name = "Контроллер резюме для пользователя", description = "Работа с резюме")
public class ResumeControllerForUser {

    @Schema(name = "Сервис резюме", description = "Содержит имплементацию методов для работы с репозиторием")
    private final ResumeServiceImpl resumeService;

    @Operation(summary = "Получение резюме по позиции", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Резюме получено согласно позиции",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('COMPANY')")
    @GetMapping("/filter/{position}")
    public Page<ResumeResponseForUser> getAllResumesByPosition(
            @PageableDefault(size = 1) Pageable pageable
            , @PathVariable("position") String position) {
        return resumeService.getAllResumesByPositionForUser(position, pageable);
    }

    @Operation(summary = "Получение списка резюме", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список загружен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('COMPANY')")
    @GetMapping
    public Page<ResumeResponseForUser> getAllResumes(@PageableDefault(size = 5) Pageable pageable) {
        return resumeService.getAllResumesForUser(pageable);
    }

    @Operation(summary = "Поиск определенного списка резюме",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Резюме получено",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('COMPANY')")
    @GetMapping("/search")
    public Page<ResumeResponseForUser> getAllResumesByTextFilter(@RequestParam("text") String text,
                                                                 @PageableDefault(size = 15) Pageable pageable) {
        return resumeService.findByTextFilterForUser(text, pageable);
    }

    @Operation(summary = "Получение резюме по id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Резюме получено",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAnyAuthority('COMPANY','USER')")
    @GetMapping(UrlConstants.ID)
    public ResumeResponseForUser getById(@PathVariable Long id) {
        return resumeService.findByIdForUser(id);
    }

    @Operation(summary = "Получение списка опыта по id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Резюме получено",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAnyAuthority('COMPANY','USER')")
    @GetMapping(UrlConstants.ID + "/experiences")
    public List<ExperienceResponseForUser> GetExperiencesByResume(@PathVariable("id") Long id) {
        return resumeService.listExperiencesByResumeForUser(id);
    }

    @Operation(summary = "Добавление резюме", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Резюме добавлено",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping
    public void addResume(@RequestBody ResumeRequest resume) {
        resumeService.save(resume);
    }

    @Operation(summary = "Изменение резюме", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Резюме изменено",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('USER')")
    @PutMapping(UrlConstants.ID)
    public void updateResume(@PathVariable Long id, @RequestBody ResumeUpdateRequest resume) {
        resume.setId(id);
        resumeService.update(resume);
    }

    @Operation(summary = "Удаление резюме", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Резюме удалено",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping(UrlConstants.ID)
    public void deleteById(@PathVariable Long id) {
        resumeService.deleteById(id);
    }

    @Operation(summary = "Фильтрация резюме по дате", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAuthority('COMPANY')")
    @GetMapping("/filter/date")
    public List<ResumeResponseForUser> findByDateCreated() {
        return resumeService.findByDateCreatedForUser();
    }
}
