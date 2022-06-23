package com.fedag.recruitmentSystem.mapper;

import com.fedag.recruitmentSystem.dto.request.UserRequest;
import com.fedag.recruitmentSystem.dto.request.UserUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.UserResponse;
import com.fedag.recruitmentSystem.model.User;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
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

  public UserResponse modelToDto(User user) {
    return mapper.map(user, UserResponse.class);
  }

  public List<UserResponse> modelToDto(List<User> user) {
    return user
        .stream()
        .map(this::modelToDto)
        .collect(Collectors.toList());
  }

  public Page<UserResponse> modelToDto(Page<User> userPage) {
    return userPage
        .map(new Function<User, UserResponse>() {
          @Override
          public UserResponse apply(User entity) {
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

  public User dtoToModel(UserResponse dto) {
    return mapper.map(dto, User.class);
  }

  public List<User> dtoToModel(List<UserResponse> dto) {
    return dto
        .stream()
        .map(this::dtoToModel)
        .collect(Collectors.toList());
  }

  @PostConstruct
  public void setupMapper() {
    mapper.createTypeMap(User.class, UserResponse.class)
            .addMappings(m -> m.skip(UserResponse::setImage)).setPostConverter(toDtoConverter());
  }

  private Converter<User, UserResponse> toDtoConverter() {
    return context -> {
      User source = context.getSource();
      UserResponse destination = context.getDestination();
      mapSpecificFields(source, destination);
      return context.getDestination();
    };
  }

  private void mapSpecificFields(User source, UserResponse destination) {
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
}
