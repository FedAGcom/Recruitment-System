package com.fedag.recruitmentSystem.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.model.Company;
import com.fedag.recruitmentSystem.repository.CompanyRepository;
import com.fedag.recruitmentSystem.service.impl.CompanyServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.junit.jupiter.api.Assertions;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

  @Mock
  private CompanyRepository companyRepository;

  @InjectMocks
  private CompanyServiceImpl companyService;

  @Test
  void testGetAllCompanies() {
    companyService.getAllCompanies();
    verify(companyRepository).findAll();
  }

  @Test
  void testGetAllCompaniesPageable() {
    Pageable pageable = Mockito.any(Pageable.class);
    companyService.getAllCompanies(pageable);
    verify(companyRepository).findAll(pageable);
  }

  @Test
  void testFindById() {
    when(companyRepository.findById(anyLong())).thenReturn(Optional.of(new Company()));
    companyService.findById(anyLong());
    verify(companyRepository).findById(anyLong());
  }

  @Test
  void testExpectedExceptionFindById() {
    assertThrows(ObjectNotFoundException.class, () -> companyService.findById(anyLong()));
  }

  @Test
  void testSave() {
    Company company = new Company();
    companyService.save(company);
    verify(companyRepository).save(company);
  }

  @Test
  void testDeleteById() {
    companyService.deleteById(anyLong());
    verify(companyRepository).deleteById(anyLong());
  }
}