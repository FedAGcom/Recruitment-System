package com.fedag.recruitmentSystem.mapper;

import com.fedag.recruitmentSystem.dto.request.UserRequest;
import com.fedag.recruitmentSystem.dto.request.UserUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.UserResponseForAdmin;
import com.fedag.recruitmentSystem.dto.response.user_response.UserResponseForUser;
import com.fedag.recruitmentSystem.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper mapper;

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(User.class, UserResponseForAdmin.class)
                .addMappings(m -> m.skip(UserResponseForAdmin::setImage))
                .setPostConverter(toDtoConverter());

        mapper.createTypeMap(User.class, UserResponseForUser.class)
                .addMappings(m -> m.skip(UserResponseForUser::setImage))
                .setPostConverter(toDtoConverterForUser());
    }

    private Converter<User, UserResponseForAdmin> toDtoConverter() {
        return context -> {
            User source = context.getSource();
            UserResponseForAdmin destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    private void mapSpecificFields(User source, UserResponseForAdmin destination) {
        String base64Encoded = null;
        if (source.getImage() != null) {
            byte[] encodeBase64 = Base64.encode(source.getImage());
            try {
                base64Encoded = new String(encodeBase64, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("Не поддерживаемый тип для кодирования картинки");
            }
        }
        destination.setImage(base64Encoded);
    }

    private Converter<User, UserResponseForUser> toDtoConverterForUser() {
        return context -> {
            User source = context.getSource();
            UserResponseForUser destination = context.getDestination();
            mapSpecificFieldsForUser(source, destination);
            return context.getDestination();
        };
    }

    private void mapSpecificFieldsForUser(User source, UserResponseForUser destination) {
        String base64Encoded = null;
        if (source.getImage() != null) {
            byte[] encodeBase64 = Base64.encode(source.getImage());
            try {
                base64Encoded = new String(encodeBase64, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("Не поддерживаемый тип для кодирования картинки");
            }
        }
        destination.setImage(base64Encoded);
    }

    public UserResponseForAdmin modelToDto(User user) {
        return mapper.map(user, UserResponseForAdmin.class);
    }

    public List<UserResponseForAdmin> modelToDto(List<User> user) {
        return user
                .stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    public Page<UserResponseForAdmin> modelToDto(Page<User> userPage) {
        return userPage
                .map(new Function<User, UserResponseForAdmin>() {
                    @Override
                    public UserResponseForAdmin apply(User entity) {
                        return modelToDto(entity);
                    }
                });
    }

    public User dtoToModel(UserRequest dto) {
        return mapper.map(dto, User.class);
    }

    public User dtoToModel(UserUpdateRequest dto) {
        return mapper.map(dto, User.class);
    }

    public User dtoToModel(UserResponseForAdmin dto) {
        return mapper.map(dto, User.class);
    }

    public List<User> dtoToModel(List<UserResponseForAdmin> dto) {
        return dto
                .stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }

    public UserResponseForUser modelToDtoForUser(User user) {
        return mapper.map(user, UserResponseForUser.class);
    }

    public Page<UserResponseForUser> modelToDtoForUser(Page<User> userPage) {
        return userPage
                .map(new Function<User, UserResponseForUser>() {
                    @Override
                    public UserResponseForUser apply(User entity) {
                        return modelToDtoForUser(entity);
                    }
                });
    }

    public List<UserResponseForUser> modelToDtoForUser(List<User> user) {
        return user
                .stream()
                .map(this::modelToDtoForUser)
                .collect(Collectors.toList());
    }
}
