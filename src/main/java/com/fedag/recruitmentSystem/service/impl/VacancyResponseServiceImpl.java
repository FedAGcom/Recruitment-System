package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.model.VacancyResponse;
import com.fedag.recruitmentSystem.repository.VacancyResponseRepository;
import com.fedag.recruitmentSystem.service.VacancyResponseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VacancyResponseServiceImpl implements VacancyResponseService<VacancyResponse> {

  private final VacancyResponseRepository vacancyResponseRepository;

  @Override
  public List<VacancyResponse> getAllVacanciesResponses() {
    return vacancyResponseRepository.findAll();
  }

  @Override
  public Page<VacancyResponse> getAllVacanciesResponses(Pageable pageable) {
    return vacancyResponseRepository.findAll(pageable);
  }

  @Override
  public VacancyResponse findById(Long id) {
    return vacancyResponseRepository
        .findById(id)
        .orElseThrow(
            () -> new ObjectNotFoundException("VacancyResponse with id: " + id + " not found")
        );
  }

  @Override
  public void save(VacancyResponse element) {
    vacancyResponseRepository.save(element);
  }

  @Override
  public void deleteById(Long id) {
    vacancyResponseRepository.deleteById(id);
  }
}
