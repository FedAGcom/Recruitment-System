package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.model.Skill;
import com.fedag.recruitmentSystem.model.Vacancy;
import com.fedag.recruitmentSystem.repository.VacancyRepository;
import com.fedag.recruitmentSystem.service.VacancyService;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VacancyServiceImpl implements VacancyService<Vacancy> {

  private final VacancyRepository vacancyRepository;

  @Override
  public Vacancy findById(Long id) {
    Optional<Vacancy> vacancy = vacancyRepository.findById(id);
    if (vacancy.isEmpty()) {
      throw new IllegalArgumentException("Vacancy is null");
    }
    return vacancy.get();
  }

  @Override
  public List<Vacancy> index() {
    return vacancyRepository.findAll();
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
