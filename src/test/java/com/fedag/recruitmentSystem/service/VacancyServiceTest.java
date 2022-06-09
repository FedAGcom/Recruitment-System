package com.fedag.recruitmentSystem.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.fedag.recruitmentSystem.dto.request.VacancyRequest;
import com.fedag.recruitmentSystem.dto.response.VacancyResponse;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.mapper.VacancyMapper;
import com.fedag.recruitmentSystem.model.Company;
import com.fedag.recruitmentSystem.model.Vacancy;
import com.fedag.recruitmentSystem.repository.VacancyRepository;
import com.fedag.recruitmentSystem.service.impl.VacancyServiceImpl;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class VacancyServiceTest {

  @Mock
  private VacancyRepository vacancyRepository;

  @Mock
  private VacancyMapper vacancyMapper;

  @InjectMocks
  private VacancyServiceImpl vacancyService;

  @Test
  void testGetAllCompanies() {
    vacancyService.getAllVacancies();
    verify(vacancyRepository).findAll();
  }

  @Test
  void testGetAllCompaniesPageable() {
    Pageable pageable = PageRequest.of(0, 5);
    when(vacancyRepository.findAll(pageable)).thenReturn(new PageImpl<>(Collections.singletonList(new Vacancy())));
    vacancyService.getAllVacancies(pageable);
    verify(vacancyRepository).findAll(pageable);
  }

  @Test
  void testFindById() {
    when(vacancyRepository.findById(anyLong())).thenReturn(Optional.of(new Vacancy()));
    vacancyService.findById(anyLong());
    verify(vacancyRepository).findById(anyLong());
  }

  @Test
  void testExpectedExceptionFindById() {
    assertThrows(ObjectNotFoundException.class, () -> vacancyService.findById(anyLong()));
  }

  @Test
  void testSave() {
    VacancyRequest vacancyRequest = new VacancyRequest();
    vacancyService.save(vacancyRequest);
    verify(vacancyRepository).save(vacancyMapper.toEntity(vacancyRequest));
  }

  @Test
  void testDeleteById() {
    vacancyService.deleteById(anyLong());
    verify(vacancyRepository).deleteById(anyLong());
  }
}
