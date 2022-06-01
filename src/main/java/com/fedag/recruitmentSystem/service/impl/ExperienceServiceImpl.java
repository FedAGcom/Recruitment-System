package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.model.Experience;
import com.fedag.recruitmentSystem.repository.ExperienceRepository;
import com.fedag.recruitmentSystem.service.ExperienceService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService<Experience> {

  private final ExperienceRepository experienceRepository;

  @Override
  public List<Experience> getAllExperience() {
    return experienceRepository.findAll();
  }

  @Override
  public Page<Experience> getAllExperience(Pageable pageable) {
    return experienceRepository.findAll(pageable);
  }

  @Override
  public Experience findById(Long id) {
    return experienceRepository
        .findById(id)
        .orElseThrow(
            () -> new ObjectNotFoundException("Experience with id: " + id + " not found")
        );
  }

  @Override
  public void save(Experience element) {
    experienceRepository.save(element);
  }

  @Override
  public void deleteById(Long id) {
    experienceRepository.deleteById(id);
  }
}
