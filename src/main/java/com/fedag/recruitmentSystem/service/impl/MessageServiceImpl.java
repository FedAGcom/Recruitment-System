package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.model.Message;
import com.fedag.recruitmentSystem.repository.MessageRepository;
import com.fedag.recruitmentSystem.service.MessageService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService<Message> {

  private final MessageRepository messageRepository;

  @Override
  public List<Message> getAllMessages() {
    return messageRepository.findAll();
  }

  @Override
  public Page<Message> getAllMessages(Pageable pageable) {
    return messageRepository.findAll(pageable);
  }

  @Override
  public Message findById(Long id) {
    return messageRepository
        .findById(id)
        .orElseThrow(
            () -> new ObjectNotFoundException("Message with id: " + id + " not found")
        );
  }

  @Override
  public void save(Message element) {
    messageRepository.save(element);
  }

  @Override
  public void deleteById(Long id) {
    messageRepository.deleteById(id);
  }
}
