package com.fedag.recruitmentSystem.controller.user_company_controller;

import com.fedag.recruitmentSystem.dto.request.CompanyChangePasswordRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.CompanyResponseForAdmin;
import com.fedag.recruitmentSystem.dto.response.user_response.CompanyResponseForUser;
import com.fedag.recruitmentSystem.enums.UrlConstants;
import com.fedag.recruitmentSystem.service.impl.CompanyServiceImpl;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = UrlConstants.MAIN_URL_USER + UrlConstants.COMPANY_URL)
@Tag(name = "Контроллер компаний для пользователя", description = "Работа с компаниями")
public class CompanyControllerForUser {

    @Schema(name = "Сервис компаний", description = "Содержит имплементацию методов для работы с репозиторием")
    private final CompanyServiceImpl companyService;

    @Operation(summary = "Получение списка компаний", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Компании загружены",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAnyAuthority('COMPANY','USER')")
    @GetMapping
    public Page<CompanyResponseForUser> getAllCompanies(@PageableDefault(size = 5) Pageable pageable) {
        return companyService.getAllCompaniesForUser(pageable);
    }

    @Operation(summary = "Получение компании по id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Компания найдена",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAnyAuthority('COMPANY','USER')")
    @GetMapping(UrlConstants.ID)
    public CompanyResponseForUser getById(@PathVariable Long id) {
        return companyService.findByIdForUser(id);
    }


    @Operation(summary = "Изменение пароля компании", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Компания изменена",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('COMPANY')")
    @PostMapping("/pass/change")
    public ResponseEntity<?> changeCompanyPassword(@Valid @RequestBody CompanyChangePasswordRequest company) {
        try {
            companyService.changePassword(company);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Request to company password change has been added successfully." +
                " Please check company email to confirm the change.",
                HttpStatus.OK);
    }

}
