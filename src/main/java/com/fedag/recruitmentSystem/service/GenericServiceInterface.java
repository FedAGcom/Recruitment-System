package com.fedag.recruitmentSystem.service;

import java.util.List;

public interface GenericServiceInterface<T> {
  T findById(Long id);
  List<T> index();
  void save(T element);
  void deleteById(Long id);
}
