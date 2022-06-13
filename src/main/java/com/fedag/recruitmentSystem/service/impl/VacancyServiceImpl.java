package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.dto.request.VacancyRequest;
import com.fedag.recruitmentSystem.dto.request.VacancyUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.VacancyResponse;
import com.fedag.recruitmentSystem.mapper.VacancyMapper;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.repository.VacancyRepository;
import com.fedag.recruitmentSystem.service.VacancyService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService<VacancyResponse, VacancyRequest, VacancyUpdateRequest> {

  private final VacancyRepository vacancyRepository;
  private final VacancyMapper vacancyMapper;

  @Override
  public List<VacancyResponse> getAllVacancies() {
    return vacancyRepository
        .findAll()
        .stream()
        .map(vacancyMapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public Page<VacancyResponse> getAllVacancies(Pageable pageable) {
    return vacancyRepository
        .findAll(pageable)
        .map(vacancyMapper::toDto);
  }

  @Override
  public VacancyResponse findById(Long id) {
    return vacancyMapper.toDto(vacancyRepository
        .findById(id)
        .orElseThrow(
            () -> new ObjectNotFoundException("Vacancy with id: " + id + " not found")
        ));
  }

  public List<VacancyResponse> findByDateCreated() {
    return vacancyMapper.toDto(vacancyRepository.findByDateCreated());
  }

  @Override
  public void save(VacancyRequest element) {
    vacancyRepository.save(vacancyMapper.toEntity(element));
  }

  @Override
  public void update(VacancyUpdateRequest element) {
    vacancyRepository.save(vacancyMapper.toEntity(element));
  }

  @Override
  public void deleteById(Long id) {
    vacancyRepository.deleteById(id);
  }
}
