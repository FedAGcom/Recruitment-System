package com.fedag.recruitmentSystem.service;

public interface AbstractServiceInterface<T, S, U> {

    T findById(Long id);

    void save(S element);

    void update(U element);

    void deleteById(Long id);
}


