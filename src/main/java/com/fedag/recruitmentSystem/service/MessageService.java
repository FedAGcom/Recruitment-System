package com.fedag.recruitmentSystem.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageService<T, S> extends AbstractServiceInterface<T, S> {

  List<T> getAllMessages();

  Page<T> getAllMessages(Pageable pageable);
}
