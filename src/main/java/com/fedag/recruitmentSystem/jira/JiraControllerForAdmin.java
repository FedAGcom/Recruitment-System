package com.fedag.recruitmentSystem.jira;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jira")
@Tag(name = "Jira контроллер для админа", description = "Работа с Jira")
public class JiraControllerForAdmin {

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
    public String getAllIssueByUserEmail() {
//        MyJiraClient myJiraClient = new MyJiraClient("larisavoytsekh65@mail.ru",
//                "xO9cibtpeWsvCBxPlYvWE8E7", "https://barsuk.atlassian.net");
       String s = myJiraClient.getAllIssueByUserEmail();
       return s;
    }
}
