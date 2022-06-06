package com.fedag.recruitmentSystem.service;

import java.util.List;

import com.fedag.recruitmentSystem.model.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResumeService<T> extends AbstractServiceInterface<T> {

  List<T> getAllResumes();

  Page<T> getAllResumes(Pageable pageable);

  Page<T> findByTextFilter(String text, Pageable pageable);

  Page<T> findByPosition(String position, Pageable pageable);
}
