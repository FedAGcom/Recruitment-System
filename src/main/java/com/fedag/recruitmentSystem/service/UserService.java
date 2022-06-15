package com.fedag.recruitmentSystem.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService<T, S, U> extends AbstractServiceInterface<T, S, U> {

  List<T> getAllUsers();

  Page<T> getAllUsers(Pageable pageable);

  T findByEmail(String email);
}