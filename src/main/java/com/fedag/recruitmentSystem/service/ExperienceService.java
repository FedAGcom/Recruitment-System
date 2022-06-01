package com.fedag.recruitmentSystem.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExperienceService<T> extends AbstractServiceInterface<T> {

  List<T> getAllExperience();

  Page<T> getAllExperience(Pageable pageable);
}
