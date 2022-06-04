package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.model.Message;
import com.fedag.recruitmentSystem.repository.MessageRepository;
import com.fedag.recruitmentSystem.service.impl.MessageServiceImpl;
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
public class MessageServiceTest {
    @Mock
    MessageRepository messageRepository;

    @InjectMocks
    MessageServiceImpl messageService;

    @Test
    void testGetAllMessages() {
        messageService.getAllMessages();
        verify(messageRepository).findAll();
    }

    @Test
    void testGetAllMessagesPageable() {
        Pageable pageable = Mockito.any(Pageable.class);
        messageService.getAllMessages(pageable);
        verify(messageRepository).findAll(pageable);
    }

    @Test
    void testFindById(){
        when(messageRepository.findById(anyLong())).thenReturn(Optional.of(new Message()));
        messageService.findById(anyLong());
        verify(messageRepository).findById(anyLong());
    }

    @Test
    void testSave() {
        Message message = new Message();
        messageService.save(message);
        verify(messageRepository).save(message);
    }

    @Test
    void testDeleteById() {
        messageService.deleteById(anyLong());
        verify(messageRepository).deleteById(anyLong());
    }
}
