package com.fedag.recruitmentSystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MessageService<T, S, U> extends AbstractServiceInterface<T, S, U> {

    List<T> getAllMessages();

    Page<T> getAllMessages(Pageable pageable);
}
