package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.model.User;
import com.fedag.recruitmentSystem.repository.UserRepository;
import com.fedag.recruitmentSystem.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

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
        User exam = new User();
        userService.save(exam);
        verify(userRepository).save(exam);
    }

    @Test
    void testDeleteUserById() {
        userService.deleteById(anyLong());
        verify(userRepository).deleteById(anyLong());
    }
}




