package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.Message;
import com.fedag.recruitmentSystem.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/message")
@Tag(name = "Контроллер сообщений", description = "Работа с сообщениями")
public class MessageController {

    @Schema(name = "Сервис сообщений", description = "Содержит имплементацию методов для работы с репозиторием")
    private final MessageService messageService;

    @Operation(summary = "Получение списка сообщений")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список загружен",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @GetMapping
    public Page<Message> getAllMessage(@PageableDefault(size = 5) Pageable pageable) {
        return messageService.getAllMessage(pageable);
    }

    @Operation(summary = "Получение сообщения по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Сообщение загружено",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @GetMapping("/{id}")
    public Message getMessageById(@PathVariable Long id) {
        return messageService.getMessageById(id);

    }

    @Operation(summary = "Добавление сообщения")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Сообщение добавлено",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PostMapping("/add")
    public Message addMessage(@RequestBody Message message) {
        return messageService.addMessage(message);
    }

    @Operation(summary = "Удаление сообщения")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Сообщение удалено",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {
        messageService.removeMessage(id);
    }
}
