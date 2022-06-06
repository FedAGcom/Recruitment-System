package com.fedag.recruitmentSystem.map;

import com.fedag.recruitmentSystem.dto.UserFeedbackResponse;
import com.fedag.recruitmentSystem.dto.UserResponse;
import com.fedag.recruitmentSystem.model.User;
import com.fedag.recruitmentSystem.model.UserFeedback;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UserFeedbackMapper {

    public UserFeedbackResponse modelToDto(UserFeedback userFeedback) {
        ModelMapper mapper = new ModelMapper();
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

    public UserFeedback dtoToModel(UserFeedbackResponse dto) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(dto, UserFeedback.class);

    }

    public List<UserFeedback> dtoToModel(List<UserFeedbackResponse> dto) {
        return dto
                .stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }
}
