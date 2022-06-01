package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.model.CompanyFeedBack;
import com.fedag.recruitmentSystem.repository.CompanyFeedbackRepository;
import com.fedag.recruitmentSystem.service.CompanyFeedbackService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyFeedbackServiceImpl implements CompanyFeedbackService<CompanyFeedBack> {

  private final CompanyFeedbackRepository companyFeedBackRepository;

  @Override
  public List<CompanyFeedBack> getAllCompanyFeedbacks() {
    return companyFeedBackRepository.findAll();
  }

  @Override
  public Page<CompanyFeedBack> getAllCompanyFeedbacks(Pageable pageable) {
    return companyFeedBackRepository.findAll(pageable);
  }

  @Override
  public CompanyFeedBack findById(Long id) {
    return companyFeedBackRepository
        .findById(id)
        .orElseThrow(
            () -> new ObjectNotFoundException("Company with id: " + id + " not found")
        );
  }

  @Override
  public void save(CompanyFeedBack element) {
    companyFeedBackRepository.save(element);
  }

  @Override
  public void deleteById(Long id) {
    companyFeedBackRepository.deleteById(id);
  }
}




