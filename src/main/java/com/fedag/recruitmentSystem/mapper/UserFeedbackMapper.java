package com.fedag.recruitmentSystem.mapper;

import com.fedag.recruitmentSystem.dto.request.UserFeedbackRequest;
import com.fedag.recruitmentSystem.dto.request.UserFeedbackUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.UserFeedbackResponseForAdmin;
import com.fedag.recruitmentSystem.dto.response.user_response.UserFeedbackResponseForUser;
import com.fedag.recruitmentSystem.model.UserFeedback;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserFeedbackMapper {

    private final ModelMapper mapper;

    public UserFeedbackResponseForAdmin modelToDto(UserFeedback userFeedback) {
        return mapper.map(userFeedback, UserFeedbackResponseForAdmin.class);
    }

    public List<UserFeedbackResponseForAdmin> modelToDto(List<UserFeedback> userFeedbackList) {
        return userFeedbackList
                .stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    public Page<UserFeedbackResponseForAdmin> modelToDto(Page<UserFeedback> userFeedbackPage) {
        return userFeedbackPage
                .map(new Function<UserFeedback, UserFeedbackResponseForAdmin>() {
                    @Override
                    public UserFeedbackResponseForAdmin apply(UserFeedback entity) {
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

    public UserFeedback dtoToModel(UserFeedbackResponseForAdmin dto) {
        return mapper.map(dto, UserFeedback.class);
    }

    public List<UserFeedback> dtoToModel(List<UserFeedbackResponseForAdmin> dto) {
        return dto
                .stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }

    public UserFeedbackResponseForUser modelToDtoForUser(UserFeedback userFeedback) {
        return mapper.map(userFeedback, UserFeedbackResponseForUser.class);
    }

    public Page<UserFeedbackResponseForUser> modelToDtoForUser(Page<UserFeedback> userFeedbackPage) {
        return userFeedbackPage
                .map(new Function<UserFeedback, UserFeedbackResponseForUser>() {
                    @Override
                    public UserFeedbackResponseForUser apply(UserFeedback entity) {
                        return modelToDtoForUser(entity);
                    }
                });
    }
}
