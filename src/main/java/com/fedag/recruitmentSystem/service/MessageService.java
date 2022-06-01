package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageService {

    public Page<Message> getAllMessage(Pageable pageable);

    public Message getMessageById(Long id);

    public Message addMessage(Message message);

    public void removeMessage(Long id);

}
