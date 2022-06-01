package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.repository.MessageRepository;
import com.fedag.recruitmentSystem.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Transactional
    @Override
    public Page<Message> getAllMessage(Pageable pageable) {
        return messageRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Message getMessageById(Long id) {
        return messageRepository.getById(id);
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
