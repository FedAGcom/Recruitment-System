package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.dao.MessageRepository;
import com.fedag.recruitmentSystem.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Transactional
    @Override
    public List<Message> getAllMessage() {
        return messageRepository.findAll();
    }

    @Transactional
    @Override
    public Message addMessage(Message message) {
        return messageRepository.save(message);
    }

    @Transactional
    @Override
    public void removeMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
