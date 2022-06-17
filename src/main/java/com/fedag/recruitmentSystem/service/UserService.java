package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.dto.request.UserChangePasswordRequest;
import com.fedag.recruitmentSystem.dto.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserService<T, S, U> extends AbstractServiceInterface<T, S, U> {

  List<T> getAllUsers();

  Page<T> getAllUsers(Pageable pageable);

  T findByEmail(String email);

  List<UserResponse> getByEntranceExamScore(int score);

  List<UserResponse> getByStars(byte stars);

  List<UserResponse> getByExperience(int max);

  void changePassword(UserChangePasswordRequest user);

  void confirmPasswordChange(Long id, String password);

  void disableById(Long id);
  
  boolean activateUser(String code);
}