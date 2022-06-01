package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.model.Vacancy;
import com.fedag.recruitmentSystem.repository.VacancyRepository;
import com.fedag.recruitmentSystem.service.VacancyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService<Vacancy> {

  private final VacancyRepository vacancyRepository;

  @Override
  public List<Vacancy> getAllVacancies() {
    return vacancyRepository.findAll();
  }

  @Override
  public Page<Vacancy> getAllVacancies(Pageable pageable) {
    return vacancyRepository.findAll(pageable);
  }

  @Override
  public Vacancy findById(Long id) {
    return vacancyRepository
        .findById(id)
        .orElseThrow(
            () -> new ObjectNotFoundException("Vacancy with id: " + id + " not found")
        );
  }

  @Override
  public void save(Vacancy element) {
    vacancyRepository.save(element);
  }

  @Override
  public void deleteById(Long id) {
    vacancyRepository.deleteById(id);
  }
}
