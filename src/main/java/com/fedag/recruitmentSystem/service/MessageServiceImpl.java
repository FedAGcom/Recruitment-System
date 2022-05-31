package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.dao.MessageDao;
import com.fedag.recruitmentSystem.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageDao messageDao;

    @Autowired
    public MessageServiceImpl(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Transactional
    @Override
    public List<Message> getAllMessage() {
        return messageDao.findAll();
    }

    @Transactional
    @Override
    public Message addMessage(Message message) {
        return messageDao.save(message);
    }

    @Transactional
    @Override
    public void removeMessage(Long id) {
        messageDao.deleteById(id);
    }
}
