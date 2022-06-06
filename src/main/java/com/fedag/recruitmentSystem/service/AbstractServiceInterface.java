package com.fedag.recruitmentSystem.service;

public interface AbstractServiceInterface<T, R> {

  T findById(Long id);

  void save(R element);

  void deleteById(Long id);
}


