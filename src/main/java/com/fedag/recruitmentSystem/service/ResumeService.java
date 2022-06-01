package com.fedag.recruitmentSystem.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResumeService<T> extends AbstractServiceInterface<T> {

  List<T> getAllResumes();

  Page<T> getAllResumes(Pageable pageable);
}
