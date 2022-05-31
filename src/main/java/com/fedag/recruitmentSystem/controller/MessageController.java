package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.Message;
import com.fedag.recruitmentSystem.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/message")
    public List<Message> getAllMessage() {
        return messageService.getAllMessage();
    }

    @PostMapping("/message/add")
    public Message addMessage(@RequestBody Message message) {
        return messageService.addMessage(message);
    }

    @DeleteMapping("message/delete/{id}")
    public void deleteMessage(@PathVariable Long id) {
        messageService.removeMessage(id);
    }
}
