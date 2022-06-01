package com.fedag.recruitmentSystem.service;

public interface GenericServiceInterface<T> {

  T findById(Long id);

  void save(T element);

  void deleteById(Long id);
}
