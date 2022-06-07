package com.fedag.recruitmentSystem.mapper;

import com.fedag.recruitmentSystem.dto.request.CompanyFeedbackRequest;
import com.fedag.recruitmentSystem.dto.response.CompanyFeedbackResponse;
import com.fedag.recruitmentSystem.model.CompanyFeedBack;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyFeedbackMapper {

  private final ModelMapper mapper;

  public CompanyFeedbackResponse modelToDto(CompanyFeedBack companyFeedBack) {
    return mapper.map(companyFeedBack, CompanyFeedbackResponse.class);
  }

  public List<CompanyFeedbackResponse> modelToDto(List<CompanyFeedBack> companyFeedBackList) {
    return companyFeedBackList
        .stream()
        .map(this::modelToDto)
        .collect(Collectors.toList());
  }

  public Page<CompanyFeedbackResponse> modelToDto(Page<CompanyFeedBack> companyFeedBacks) {
    return companyFeedBacks
        .map(new Function<CompanyFeedBack, CompanyFeedbackResponse>() {
          @Override
          public CompanyFeedbackResponse apply(CompanyFeedBack entity) {
            return modelToDto(entity);
          }
        });
  }

  public CompanyFeedBack dtoToModel(CompanyFeedbackRequest dto) {
    return mapper.map(dto, CompanyFeedBack.class);
  }

  public CompanyFeedBack dtoToModel(CompanyFeedbackResponse dto) {
    return mapper.map(dto, CompanyFeedBack.class);
  }

  public List<CompanyFeedBack> dtoToModel(List<CompanyFeedbackResponse> dto) {
    return dto
        .stream()
        .map(this::dtoToModel)
        .collect(Collectors.toList());
  }
}
