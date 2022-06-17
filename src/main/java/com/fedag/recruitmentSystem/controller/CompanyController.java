package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.dto.request.CompanyChangePasswordRequest;
import com.fedag.recruitmentSystem.dto.request.CompanyRequest;
import com.fedag.recruitmentSystem.dto.request.UserChangePasswordRequest;
import com.fedag.recruitmentSystem.dto.response.CompanyResponse;
import com.fedag.recruitmentSystem.dto.response.UserResponse;
import com.fedag.recruitmentSystem.enums.Role;
import com.fedag.recruitmentSystem.exception.EntityIsExistsException;
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
@RequestMapping(value = "/api/companies")
@Tag(name = "Контроллер компаний", description = "Работа с компаниями")
public class CompanyController {

    @Schema(name = "Сервис компаний", description = "Содержит имплементацию методов для работы с репозиторием")
    private final CompanyServiceImpl companyService;

    @Operation(summary = "Получение списка компаний", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Компании загружены",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('READ')")
    @GetMapping
    public Page<CompanyResponse> getAllCompanies(@PageableDefault(size = 5) Pageable pageable) {
        return companyService.getAllCompanies(pageable);
    }

    @Operation(summary = "Получение компании по id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Компания найдена",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/{id}")
    public CompanyResponse getById(@PathVariable Long id) {
        return companyService.findById(id);
    }

    @Operation(summary = "Удаление кампании", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Компания загружены",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('WRITE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable Long id) {
        CompanyResponse company = companyService.findById(id);
        if(company.getRole().equals(Role.ADMIN_INACTIVE)
        || company.getRole().equals(Role.USER_INACTIVE) ) {
            return new ResponseEntity<>("Company already in inactive state.", HttpStatus.OK);
        }
        companyService.disableById(id);
        return new ResponseEntity<>("Company set to inactive state successfully.", HttpStatus.OK);
    }

    @Operation(summary = "Добавление компании")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Компания добавлена",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PostMapping
    public ResponseEntity<?> addCompany(@Valid @RequestBody CompanyRequest companyRequest) {
        try {
            companyService.save(companyRequest);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Company has been added successfully." +
                " Please check your email to confirm the registration.",
                HttpStatus.OK); //redirect /api/success-registration
    }

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

    @Operation(summary = "Изменение компании", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Компания изменена",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('WRITE')")
    @PutMapping("/{id}")
    public void updateCompany(@PathVariable Long id,  @Valid @RequestBody CompanyRequest companyRequest) {
        companyRequest.setId(id);
        companyService.save(companyRequest);
    }
}
