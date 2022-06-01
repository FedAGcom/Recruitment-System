package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.Message;
import com.fedag.recruitmentSystem.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/message")
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public Page<Message> getAllMessage(@PageableDefault(size = 5) Pageable pageable) {
        return messageService.getAllMessage(pageable);
    }

    @GetMapping("/{id}")
    public Message getMessageById(@PathVariable Long id) {
        return messageService.getMessageById(id);

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
