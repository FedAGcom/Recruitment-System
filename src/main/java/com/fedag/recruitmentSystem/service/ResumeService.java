package com.fedag.recruitmentSystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ResumeService<T> extends AbstractServiceInterface<T> {

    List<T> getAllResumes();

    Page<T> getAllResumes(Pageable pageable);

    Page<T> getAllResumesByPosition(String position, Pageable pageable);
}
