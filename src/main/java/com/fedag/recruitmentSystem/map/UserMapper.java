package com.fedag.recruitmentSystem.map;

import com.fedag.recruitmentSystem.dto.UserRequest;
import com.fedag.recruitmentSystem.dto.UserResponse;
import com.fedag.recruitmentSystem.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserResponse modelToDto(User user) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(user, UserResponse.class);
    }

    public List<UserResponse> modelToDto(List<User> user) {
        return user
                .stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    public Page<UserResponse> modelToDto(Page<User> all) {
        return all
                .map(new Function<User, UserResponse>() {
                    @Override
                    public UserResponse apply(User entity) {
                        return modelToDto(entity);
                    }
                });
    }

    public User dtoToModel(UserResponse dto) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(dto, User.class);

    }

    public List<User> dtoToModel(List<UserResponse> dto) {
        return dto
                .stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }
}
