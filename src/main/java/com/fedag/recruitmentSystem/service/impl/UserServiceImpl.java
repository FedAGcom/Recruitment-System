package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.model.Exam;
import com.fedag.recruitmentSystem.model.User;
import com.fedag.recruitmentSystem.repository.UserRepository;
import com.fedag.recruitmentSystem.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService<User> {

  private final UserRepository userRepository;

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public Page<User> getAllUsers(Pageable pageable) {
    return userRepository.findAll(pageable);
  }

  public List<User> getByEntranceExamScore(int score) {
    return userRepository.findByEntranceExamScore(score);
  }

  @Override
  public User findById(Long id) {
    return userRepository
        .findById(id)
        .orElseThrow(
            () -> new ObjectNotFoundException("User with id: " + id + " not found")
        );
  }

  @Override
  public void save(User element) {
    userRepository.save(element);
  }

  @Override
  public void deleteById(Long id) {
    userRepository.deleteById(id);
  }
}