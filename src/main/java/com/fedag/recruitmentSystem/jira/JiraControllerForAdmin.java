package com.fedag.recruitmentSystem.jira;

import com.fedag.recruitmentSystem.enums.UrlConstants;
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

    @Operation(summary = "Получение списка задач пользователя",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Компании загружены",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public List<IssueDto> getAllIssueByUserEmail() {
        return myJiraClient.getAllIssueByUserEmail();
    }
}
