package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.model.CompanyFeedBack;
import com.fedag.recruitmentSystem.model.Message;

import java.util.List;

public interface MessageService {
    public List<Message> getAllMessage();

    public Message addMessage(Message message);

    public void removeMessage(Long id);
}
