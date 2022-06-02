package com.fedag.recruitmentSystem.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.model.Company;
import com.fedag.recruitmentSystem.model.Vacancy;
import com.fedag.recruitmentSystem.repository.CompanyRepository;
import com.fedag.recruitmentSystem.repository.VacancyRepository;
import com.fedag.recruitmentSystem.service.impl.CompanyServiceImpl;
import com.fedag.recruitmentSystem.service.impl.VacancyServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class VacancyServiceTest {

  @Mock
  private VacancyRepository vacancyRepository;

  @InjectMocks
  private VacancyServiceImpl vacancyService;

  @Test
  void testGetAllCompanies() {
    vacancyService.getAllVacancies();
    verify(vacancyRepository).findAll();
  }

  @Test
  void testGetAllCompaniesPageable() {
    Pageable pageable = Mockito.any(Pageable.class);
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
    Vacancy vacancy = new Vacancy();
    vacancyService.save(vacancy);
    verify(vacancyRepository).save(vacancy);
  }

  @Test
  void testDeleteById() {
    vacancyService.deleteById(anyLong());
    verify(vacancyRepository).deleteById(anyLong());
  }
}