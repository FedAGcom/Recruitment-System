package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.domain.dto.VacancyDto;
import com.fedag.recruitmentSystem.domain.mapper.VacancyMapper;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.repository.VacancyRepository;
import com.fedag.recruitmentSystem.service.VacancyService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService<VacancyDto> {

  private final VacancyRepository vacancyRepository;
  private final VacancyMapper vacancyMapper;

  @Override
  public List<VacancyDto> getAllVacancies() {
    return vacancyRepository
        .findAll()
        .stream()
        .map(vacancyMapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public Page<VacancyDto> getAllVacancies(Pageable pageable) {
    return vacancyRepository
        .findAll(pageable)
        .map(vacancyMapper::toDto);
  }

  @Override
  public VacancyDto findById(Long id) {
    return vacancyMapper.toDto(vacancyRepository
        .findById(id)
        .orElseThrow(
            () -> new ObjectNotFoundException("Vacancy with id: " + id + " not found")
        ));
  }

  @Override
  public void save(VacancyDto vacancyDto) {
    vacancyRepository.save(vacancyMapper.toEntity(vacancyDto));
  }

  @Override
  public void deleteById(Long id) {
    vacancyRepository.deleteById(id);
  }
}
