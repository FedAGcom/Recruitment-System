package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.repository.MessageRepository;
import com.fedag.recruitmentSystem.model.Message;
import com.fedag.recruitmentSystem.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public Page<Message> getAllMessage(Pageable pageable) {
        return messageRepository.findAll(pageable);
    }

    @Override
    public Message getMessageById(Long id) {
        return messageRepository.getById(id);
    }

    @Override
    public Message addMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void removeMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
