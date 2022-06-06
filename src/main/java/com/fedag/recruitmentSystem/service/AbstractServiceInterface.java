package com.fedag.recruitmentSystem.service;

public interface AbstractServiceInterface<T, S> {

  T findById(Long id);

  void save(S element);

  void deleteById(Long id);
}


