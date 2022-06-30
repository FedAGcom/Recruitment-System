package com.fedag.recruitmentSystem.jira;

import com.fedag.recruitmentSystem.enums.UrlConstants;
import com.fedag.recruitmentSystem.service.impl.UserServiceImpl;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = UrlConstants.MAIN_URL_ADMIN + UrlConstants.JIRA_URL)
@Tag(name = "Jira контроллер для админа", description = "Работа с Jira")
public class JiraControllerForAdmin {

    @Schema(name = "Jira клиент", description = "Содержит имплементацию методов для работы с jira")
    private final MyJiraClient myJiraClient;

    @Schema(name = "Сервис пользователей",
            description = "Содержит имплементацию методов для работы с репозиторием")
    private final UserServiceImpl userService;

    @Operation(summary = "Получение списка задач пользователя по id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Задачи загружены",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("by_user_id" + UrlConstants.ID)
    public List<IssueDto> getAllIssueByUserId(@PathVariable Long id) {
        String email = userService.findById(id).getEmail();
        return myJiraClient.getAllIssueByUserEmail(email);
    }

    @Operation(summary = "Получение списка задач пользователя по email",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Задачи загружены",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("by_user_email" + "/{email}")
    public List<IssueDto> getAllIssueByUserEmail(@PathVariable String email) {
        return myJiraClient.getAllIssueByUserEmail(email);
    }
}
