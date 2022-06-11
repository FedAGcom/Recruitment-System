package com.fedag.recruitmentSystem.service;

import java.util.List;

import com.fedag.recruitmentSystem.dto.request.UserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService<T, S, U> extends AbstractServiceInterface<T, S, U> {

  List<T> getAllUsers();

  Page<T> getAllUsers(Pageable pageable);

  boolean saveUser(UserRequest user);

  boolean activateUser(String code);
}