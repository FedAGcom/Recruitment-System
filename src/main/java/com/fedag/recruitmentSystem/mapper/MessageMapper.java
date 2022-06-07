package com.fedag.recruitmentSystem.mapper;

import com.fedag.recruitmentSystem.dto.request.MessageRequest;
import com.fedag.recruitmentSystem.dto.response.MessageResponse;
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

  public MessageResponse modelToDto(Message message) {
    return mapper.map(message, MessageResponse.class);
  }

  public List<MessageResponse> modelToDto(List<Message> messages) {
    return messages.stream()
        .map(this::modelToDto)
        .collect(Collectors.toList());
  }

  public Page<MessageResponse> modelToDto(Page<Message> messages) {
    return messages.map(new Function<Message, MessageResponse>() {
      @Override
      public MessageResponse apply(Message message) {
        return modelToDto(message);
      }
    });
  }

  public Message dtoToModel(MessageRequest dto) {
    return mapper.map(dto, Message.class);
  }

  public Message dtoToModel(MessageResponse response) {
    return mapper.map(response, Message.class);
  }

  public List<Message> dtoToModel(List<MessageResponse> responses) {
    return responses.stream()
        .map(this::dtoToModel)
        .collect(Collectors.toList());
  }
}
