package com.fedag.recruitmentSystem.mapper;

import com.fedag.recruitmentSystem.dto.request.UserFeedbackRequest;
import com.fedag.recruitmentSystem.dto.request.UserFeedbackUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.UserFeedbackResponse;
import com.fedag.recruitmentSystem.model.UserFeedback;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFeedbackMapper {

  private final ModelMapper mapper;

  public UserFeedbackResponse modelToDto(UserFeedback userFeedback) {
    return mapper.map(userFeedback, UserFeedbackResponse.class);
  }

  public List<UserFeedbackResponse> modelToDto(List<UserFeedback> userFeedbackList) {
    return userFeedbackList
        .stream()
        .map(this::modelToDto)
        .collect(Collectors.toList());
  }

  public Page<UserFeedbackResponse> modelToDto(Page<UserFeedback> userFeedbackPage) {
    return userFeedbackPage
        .map(new Function<UserFeedback, UserFeedbackResponse>() {
          @Override
          public UserFeedbackResponse apply(UserFeedback entity) {
            return modelToDto(entity);
          }
        });
  }

  public UserFeedback dtoToModel(UserFeedbackRequest dto) {
    return mapper.map(dto, UserFeedback.class);
  }

  public UserFeedback dtoToModel(UserFeedbackUpdateRequest dto) {
    return mapper.map(dto, UserFeedback.class);
  }

  public UserFeedback dtoToModel(UserFeedbackResponse dto) {
    return mapper.map(dto, UserFeedback.class);
  }

  public List<UserFeedback> dtoToModel(List<UserFeedbackResponse> dto) {
    return dto
        .stream()
        .map(this::dtoToModel)
        .collect(Collectors.toList());
  }
}
