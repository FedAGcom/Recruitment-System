package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.dto.VacancyResponseRequest;
import com.fedag.recruitmentSystem.dto.VacancyResponseResponse;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.map.VacancyResponseMapper;
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
public class VacancyResponseServiceImpl implements VacancyResponseService<VacancyResponseResponse, VacancyResponseRequest> {

  private final VacancyResponseRepository vacancyResponseRepository;
  private final VacancyResponseMapper vacancyResponseMapper;

  @Override
  public List<VacancyResponseResponse> getAllVacanciesResponses() {
    return vacancyResponseMapper.modelToDto(vacancyResponseRepository.findAll());
  }

  @Override
  public Page<VacancyResponseResponse> getAllVacanciesResponses(Pageable pageable) {
    return vacancyResponseMapper.modelToDto(vacancyResponseRepository.findAll(pageable));
  }

  @Override
  public VacancyResponseResponse findById(Long id) {
    VacancyResponse vacancyResponse = vacancyResponseRepository
        .findById(id)
        .orElseThrow(
            () -> new ObjectNotFoundException("VacancyResponse with id: " + id + " not found")
        );
    return vacancyResponseMapper.modelToDto(vacancyResponse);
  }

  @Override
  public void save(VacancyResponseRequest element) {
    VacancyResponse vacancyResponse = vacancyResponseMapper.dtoToModel(element);
    vacancyResponseRepository.save(vacancyResponse);
  }

  @Override
  public void deleteById(Long id) {
    vacancyResponseRepository.deleteById(id);
  }
}
