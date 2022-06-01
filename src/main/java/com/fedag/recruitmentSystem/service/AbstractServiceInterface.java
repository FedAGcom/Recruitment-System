package com.fedag.recruitmentSystem.service;

public interface AbstractServiceInterface<T> {

  T findById(Long id);

  void save(T element);

  void deleteById(Long id);
}


