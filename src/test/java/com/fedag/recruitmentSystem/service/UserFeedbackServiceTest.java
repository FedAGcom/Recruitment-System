package com.fedag.recruitmentSystem.service;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;

import com.fedag.recruitmentSystem.dto.request.UserFeedbackRequest;
import com.fedag.recruitmentSystem.mapper.UserFeedbackMapper;
import com.fedag.recruitmentSystem.mapper.UserMapper;
import com.fedag.recruitmentSystem.model.UserFeedback;
import com.fedag.recruitmentSystem.repository.UserFeedbackRepository;
import com.fedag.recruitmentSystem.service.impl.UserFeedbackServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class UserFeedbackServiceTest {
    @Mock
    private UserFeedbackRepository userFeedbackRepository;

    @InjectMocks
    private UserFeedbackServiceImpl userFeedbackService;

    @Mock
    private UserFeedbackMapper userFeedbackMapper;

    @Test
    void testGetAllUserFeedback(){
        userFeedbackService.getAllUserFeedbacks();
        verify(userFeedbackRepository).findAll();
    }

    @Test
    void testGetAllUserFeedbackPageable(){
        Pageable pageable = Mockito.any(Pageable.class);
        userFeedbackService.getAllUserFeedbacks(pageable);
        verify(userFeedbackRepository).findAll(pageable);
    }

    @Test
    void testGetUserFeedbackById(){
        when(userFeedbackRepository.findById(anyLong())).thenReturn(Optional.of(new UserFeedback()));
        userFeedbackService.findById(anyLong());
        verify(userFeedbackRepository).findById(anyLong());
    }

    @Test
    void testUserFeedbackSave(){
        UserFeedbackRequest userFeedbackRequest = new UserFeedbackRequest();
        userFeedbackService.save(userFeedbackRequest);
        UserFeedback userFeedback = userFeedbackMapper.dtoToModel(userFeedbackRequest);
        verify(userFeedbackRepository).save(userFeedback);
    }

    @Test
    void testDeleteUserFeedbackById(){
        userFeedbackService.deleteById(anyLong());
        verify(userFeedbackRepository).deleteById(anyLong());
    }
}
