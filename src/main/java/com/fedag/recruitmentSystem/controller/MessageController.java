package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.Message;
import com.fedag.recruitmentSystem.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/message")
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public List<Message> getAllMessage() {
        return messageService.getAllMessage();
    }

    @PostMapping("/add")
    public Message addMessage(@RequestBody Message message) {
        return messageService.addMessage(message);
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {
        messageService.removeMessage(id);
    }
}
