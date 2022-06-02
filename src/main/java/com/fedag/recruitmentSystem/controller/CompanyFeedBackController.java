package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.CompanyFeedBack;
import com.fedag.recruitmentSystem.service.CompanyFeedBackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/companies/feedback")
@Tag(name = "Контроллер отзывов о компаниях", description = "Работа с отзывами")
public class CompanyFeedBackController {

    @Schema(name = "Сервис отзывов", description = "Содержит имплементацию методов для работы с отзывами")
    private final CompanyFeedBackService companyFeedBackService;

    @Operation(summary = "Получение списка отзывов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отзывы загружены",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @GetMapping
    public Page<CompanyFeedBack> getAllCompanyFeedBack(Pageable pageable) {
        return companyFeedBackService.getAllCompanyFeedBack(pageable);
    }

    @Operation(summary = "Получение отзыва по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отзыв найден",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @GetMapping("/{id}")
    public CompanyFeedBack getCompanyFeedBackById(@PathVariable Long id) {
        return companyFeedBackService.getCompanyFeedBackById(id);
    }

    @Operation(summary = "Добавление отзыва")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отзыв добавлен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PostMapping("/save")
    public CompanyFeedBack addCompanyFeedBack(@RequestBody CompanyFeedBack companyFeedBack) {
        return companyFeedBackService.addCompanyFeedBack(companyFeedBack);
    }

    @Operation(summary = "Удаление отзыва")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отзыв удален",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @DeleteMapping("/{id}")
    public void deleteCompanyFeedBack(@PathVariable Long id) {
        companyFeedBackService.removeCompanyFeedBack(id);
    }

}
