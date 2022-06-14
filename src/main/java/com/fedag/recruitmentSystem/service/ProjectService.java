package com.fedag.recruitmentSystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService<T, S, U> extends AbstractServiceInterface<T, S, U> {

    List<T> getAllProjects();

    Page<T> getAllProjects(Pageable pageable);
}
