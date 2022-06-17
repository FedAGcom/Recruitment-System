package com.fedag.recruitmentSystem.service;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.fedag.recruitmentSystem.model.CompanyFeedBack;
import com.fedag.recruitmentSystem.repository.CompanyFeedbackRepository;
import com.fedag.recruitmentSystem.service.impl.CompanyFeedbackServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class CompanyFeedbackServiceTest {

  @Mock
  CompanyFeedbackRepository companyFeedbackRepository;

  @InjectMocks
  CompanyFeedbackServiceImpl companyFeedbackService;

  @Test
  void testGetAllCompanyFeedbacks() {
    companyFeedbackService.getAllCompanyFeedbacks();
    verify(companyFeedbackRepository).findAll();
  }

  @Test
  void testGetAllCompanyFeedbacksPageable() {
    Pageable pageable = Mockito.any(Pageable.class);
    companyFeedbackService.getAllCompanyFeedbacks(pageable);
    verify(companyFeedbackRepository).findAll(pageable);
  }

  @Test
  void testFindById() {
    when(companyFeedbackRepository.findById(anyLong())).thenReturn(Optional.of(new CompanyFeedBack()));
    companyFeedbackService.findById(anyLong());
    verify(companyFeedbackRepository).findById(anyLong());
  }

  @Test
  void testSave() {
//        CompanyFeedBack companyFeedBack = new CompanyFeedBack();
//        companyFeedbackService.save(companyFeedBack);
//        verify(companyFeedbackRepository).save(companyFeedBack);
  }

  @Test
  void testDeleteById() {
    companyFeedbackService.deleteById(anyLong());
    verify(companyFeedbackRepository).deleteById(anyLong());
  }
}
