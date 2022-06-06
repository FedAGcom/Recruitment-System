package com.fedag.recruitmentSystem.map;

import com.fedag.recruitmentSystem.dto.CompanyFeedbackResponse;
import com.fedag.recruitmentSystem.dto.UserFeedbackResponse;
import com.fedag.recruitmentSystem.model.CompanyFeedBack;
import com.fedag.recruitmentSystem.model.UserFeedback;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CompanyFeedbackMapper {

    public CompanyFeedbackResponse modelToDto(CompanyFeedBack companyFeedBack) {
        ModelMapper mapper = new ModelMapper();
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

    public CompanyFeedBack dtoToModel(CompanyFeedbackResponse dto) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(dto, CompanyFeedBack.class);

    }

    public List<CompanyFeedBack> dtoToModel(List<CompanyFeedbackResponse> dto) {
        return dto
                .stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }
}
