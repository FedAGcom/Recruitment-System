package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.dto.request.MessageRequest;
import com.fedag.recruitmentSystem.dto.request.MessageUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.MessageResponseForAdmin;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.mapper.MessageMapper;
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
public class MessageServiceImpl implements MessageService<MessageResponseForAdmin,
        MessageRequest, MessageUpdateRequest> {

  private final MessageRepository messageRepository;
  private final MessageMapper messageMapper;

  @Override
  public List<MessageResponseForAdmin> getAllMessages() {
    return messageMapper.modelToDto(messageRepository.findAll());
  }

  @Override
  public Page<MessageResponseForAdmin> getAllMessages(Pageable pageable) {
    return messageMapper.modelToDto(messageRepository.findAll(pageable));
  }

  @Override
  public MessageResponseForAdmin findById(Long id) {
    Message message = messageRepository
        .findById(id)
        .orElseThrow(
            () -> new ObjectNotFoundException("Message with id: " + id + " not found")
        );
    return messageMapper.modelToDto(message);
  }

  @Override
  public void save(MessageRequest element) {
    Message message = messageMapper.dtoToModel(element);
    messageRepository.save(message);
  }

  @Override
  public void update(MessageUpdateRequest element) {
    Message message = messageMapper.dtoToModel(element);
    messageRepository.save(message);
  }

  @Override
  public void deleteById(Long id) {
    messageRepository.deleteById(id);
  }
}
