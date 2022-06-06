package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.dto.MessageResponse;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.map.MessageMapper;
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
public class MessageServiceImpl implements MessageService<MessageResponse> {

  private final MessageRepository messageRepository;
  private final MessageMapper messageMapper;

  @Override
  public List<MessageResponse> getAllMessages() {
    return messageMapper.modelToDto(messageRepository.findAll());
  }

  @Override
  public Page<MessageResponse> getAllMessages(Pageable pageable) {
    return messageMapper.modelToDto(messageRepository.findAll(pageable));
  }

  @Override
  public MessageResponse findById(Long id) {
    Message message = messageRepository
        .findById(id)
        .orElseThrow(
            () -> new ObjectNotFoundException("Message with id: " + id + " not found")
        );
    return messageMapper.modelToDto(message);
  }

  @Override
  public void save(MessageResponse messageResponse) {
    Message message = messageMapper.dtoToModel(messageResponse);
    messageRepository.save(message);
  }

  @Override
  public void deleteById(Long id) {
    messageRepository.deleteById(id);
  }
}
