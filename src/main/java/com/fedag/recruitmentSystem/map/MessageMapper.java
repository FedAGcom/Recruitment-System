package com.fedag.recruitmentSystem.map;

import com.fedag.recruitmentSystem.dto.MessageResponse;
import com.fedag.recruitmentSystem.model.Message;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class MessageMapper {

    public MessageResponse modelToDto(Message message) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(message, MessageResponse.class);
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

    public Message dtoToModel(MessageResponse response) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(response, Message.class);
    }

    public List<Message> dtoToModel(List<MessageResponse> responses) {
        return responses.stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }
}
