package com.fedag.recruitmentSystem.mapper;

import com.fedag.recruitmentSystem.dto.request.MessageRequest;
import com.fedag.recruitmentSystem.dto.request.MessageUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.MessageResponseForAdmin;
import com.fedag.recruitmentSystem.model.Message;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class MessageMapper {

  private final ModelMapper mapper;

  public MessageResponseForAdmin modelToDto(Message message) {
    return mapper.map(message, MessageResponseForAdmin.class);
  }

  public List<MessageResponseForAdmin> modelToDto(List<Message> messages) {
    return messages.stream()
        .map(this::modelToDto)
        .collect(Collectors.toList());
  }

  public Page<MessageResponseForAdmin> modelToDto(Page<Message> messages) {
    return messages.map(new Function<Message, MessageResponseForAdmin>() {
      @Override
      public MessageResponseForAdmin apply(Message message) {
        return modelToDto(message);
      }
    });
  }

  public Message dtoToModel(MessageRequest dto) {
    return mapper.map(dto, Message.class);
  }

  public Message dtoToModel(MessageUpdateRequest dto) {
    return mapper.map(dto, Message.class);
  }

  public Message dtoToModel(MessageResponseForAdmin response) {
    return mapper.map(response, Message.class);
  }

  public List<Message> dtoToModel(List<MessageResponseForAdmin> responses) {
    return responses.stream()
        .map(this::dtoToModel)
        .collect(Collectors.toList());
  }
}
