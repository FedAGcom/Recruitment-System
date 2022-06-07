package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.dto.CompanyFeedbackRequest;
import com.fedag.recruitmentSystem.dto.CompanyFeedbackResponse;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.mapper.CompanyFeedbackMapper;
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
public class CompanyFeedbackServiceImpl implements CompanyFeedbackService<CompanyFeedbackResponse, CompanyFeedbackRequest> {

  private final CompanyFeedbackRepository companyFeedBackRepository;
  private final CompanyFeedbackMapper companyFeedbackMapper;

  @Override
  public List<CompanyFeedbackResponse> getAllCompanyFeedbacks() {
    return companyFeedbackMapper.modelToDto(companyFeedBackRepository.findAll());
  }

  @Override
  public Page<CompanyFeedbackResponse> getAllCompanyFeedbacks(Pageable pageable) {
    return companyFeedbackMapper.modelToDto(companyFeedBackRepository.findAll(pageable));
  }

  @Override
  public CompanyFeedbackResponse findById(Long id) {
    CompanyFeedBack companyFeedBack = companyFeedBackRepository
        .findById(id)
        .orElseThrow(
            () -> new ObjectNotFoundException("Company with id: " + id + " not found")
        );
    return companyFeedbackMapper.modelToDto(companyFeedBack);
  }

  @Override
  public void save(CompanyFeedbackRequest element) {
    CompanyFeedBack companyFeedBack = companyFeedbackMapper.dtoToModel(element);
    companyFeedBackRepository.save(companyFeedBack);
  }

  @Override
  public void deleteById(Long id) {
    companyFeedBackRepository.deleteById(id);
  }
}




