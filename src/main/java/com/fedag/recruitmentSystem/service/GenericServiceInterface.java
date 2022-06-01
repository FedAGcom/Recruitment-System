package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.model.Exam;
import com.fedag.recruitmentSystem.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface GenericServiceInterface<T> {

    T findById(Long id);

    void save(T element);

    void deleteById(Long id);
}


