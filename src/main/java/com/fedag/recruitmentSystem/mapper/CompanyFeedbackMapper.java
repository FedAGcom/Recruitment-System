package com.fedag.recruitmentSystem.mapper;

import com.fedag.recruitmentSystem.dto.request.CompanyFeedbackRequest;
import com.fedag.recruitmentSystem.dto.request.CompanyFeedbackUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.CompanyFeedbackResponseForAdmin;
import com.fedag.recruitmentSystem.dto.response.user_response.CompanyFeedbackResponseForUser;
import com.fedag.recruitmentSystem.model.CompanyFeedBack;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompanyFeedbackMapper {

    private final ModelMapper mapper;

    public CompanyFeedbackResponseForAdmin modelToDto(CompanyFeedBack companyFeedBack) {
        return mapper.map(companyFeedBack, CompanyFeedbackResponseForAdmin.class);
    }

    public List<CompanyFeedbackResponseForAdmin> modelToDto(List<CompanyFeedBack> companyFeedBackList) {
        return companyFeedBackList
                .stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    public Page<CompanyFeedbackResponseForAdmin> modelToDto(Page<CompanyFeedBack> companyFeedBacks) {
        return companyFeedBacks
                .map(new Function<CompanyFeedBack, CompanyFeedbackResponseForAdmin>() {
                    @Override
                    public CompanyFeedbackResponseForAdmin apply(CompanyFeedBack entity) {
                        return modelToDto(entity);
                    }
                });
    }

    public CompanyFeedBack dtoToModel(CompanyFeedbackRequest dto) {
        return mapper.map(dto, CompanyFeedBack.class);
    }

    public CompanyFeedBack dtoToModel(CompanyFeedbackUpdateRequest dto) {
        return mapper.map(dto, CompanyFeedBack.class);
    }

    public CompanyFeedBack dtoToModel(CompanyFeedbackResponseForAdmin dto) {
        return mapper.map(dto, CompanyFeedBack.class);
    }

    public List<CompanyFeedBack> dtoToModel(List<CompanyFeedbackResponseForAdmin> dto) {
        return dto
                .stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }

    public Page<CompanyFeedbackResponseForUser> modelToDtoForUser(Page<CompanyFeedBack> companyFeedBacks) {
        return companyFeedBacks
                .map(new Function<CompanyFeedBack, CompanyFeedbackResponseForUser>() {
                    @Override
                    public CompanyFeedbackResponseForUser apply(CompanyFeedBack entity) {
                        return modelToDtoForUser(entity);
                    }
                });
    }

    public CompanyFeedbackResponseForUser modelToDtoForUser(CompanyFeedBack companyFeedBack) {
        return mapper.map(companyFeedBack, CompanyFeedbackResponseForUser.class);
    }
}
