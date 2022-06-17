package com.fedag.recruitmentSystem.service;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.fedag.recruitmentSystem.dto.request.UserRequest;
import com.fedag.recruitmentSystem.mapper.UserMapper;
import com.fedag.recruitmentSystem.model.User;
import com.fedag.recruitmentSystem.repository.UserRepository;
import com.fedag.recruitmentSystem.service.impl.UserServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserServiceImpl userService;

  @Mock
  private UserMapper userMapper;

  @Test
  void testGetAllUsers() {
    userService.getAllUsers();
    verify(userRepository).findAll();
  }

  @Test
  void testGetAllUsersPageable() {
    Pageable pageable = Mockito.any(Pageable.class);
    userService.getAllUsers(pageable);
    verify(userRepository).findAll(pageable);
  }

  @Test
  void testGetUsersById() {
    when(userRepository.findById(anyLong())).thenReturn(Optional.of(new User()));
    userService.findById(anyLong());
    verify(userRepository).findById(anyLong());
  }

  @Test
  void testUserSave() {
    UserRequest userRequest = new UserRequest();
    userService.save(userRequest);
    User user = userMapper.dtoToModel(userRequest);
    verify(userRepository).save(user);
  }

  @Test
  void testDeleteUserById() {
    userService.deleteById(anyLong());
    verify(userRepository).deleteById(anyLong());
  }
}




