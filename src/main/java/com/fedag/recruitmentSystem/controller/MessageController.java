package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.Message;
import com.fedag.recruitmentSystem.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/messages")
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

  //TODO void method
  @PostMapping
  public Message addMessage(@RequestBody Message message) {
    return messageService.addMessage(message);
  }

  //TODO void method
  @PutMapping
  public Message updateMessage(@RequestBody Message message) {
    return messageService.addMessage(message);
  }

  @DeleteMapping("/{id}")
  public void deleteMessage(@PathVariable Long id) {
    messageService.removeMessage(id);
  }
}
