package com.fedag.recruitmentSystem.controller.admin_controller;

import com.fedag.recruitmentSystem.enums.UrlConstants;
import com.fedag.recruitmentSystem.model.Question;
import com.fedag.recruitmentSystem.model.TestForm;
import com.fedag.recruitmentSystem.service.impl.QuestionServiceImpl;
import com.fedag.recruitmentSystem.service.impl.TestFormServiceImpl;
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

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = UrlConstants.MAIN_URL_ADMIN + UrlConstants.TEST_FORM_URL)
@Tag(name = "Контроллер тестовый для админа", description = "Все CRUD операции с вопросами")
public class TestFormControllerForAdmin {

    @Schema(name = "Сервис вопросов", description = "Содержит реализацию методов для работы с репозиторием")
    private final TestFormServiceImpl testFormService;

    @Operation(summary = "Добавление вопроса", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Вопрос добавлен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('ADMIN')") // поменять статус на WRITE в релизе
    @GetMapping("/{language}")
    private TestForm create(@PathVariable(name = "language") String language){
       return testFormService.createForm(language);
    }

}
