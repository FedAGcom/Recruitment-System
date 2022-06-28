package com.fedag.recruitmentSystem.mapper;

import com.fedag.recruitmentSystem.dto.request.VacancyResponseRequest;
import com.fedag.recruitmentSystem.dto.request.VacancyResponseUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.VacancyResponseResponseForAdmin;
import com.fedag.recruitmentSystem.dto.response.user_response.VacancyResponseResponseForUser;
import com.fedag.recruitmentSystem.model.VacancyResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VacancyResponseMapper {

    private final ModelMapper mapper;

    public VacancyResponseResponseForAdmin modelToDto(VacancyResponse vacancyResponse) {
        return mapper.map(vacancyResponse, VacancyResponseResponseForAdmin.class);
    }

    public List<VacancyResponseResponseForAdmin> modelToDto(List<VacancyResponse> vacancyResponses) {
        return vacancyResponses.stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    public Page<VacancyResponseResponseForAdmin> modelToDto(Page<VacancyResponse> vacancyResponses) {
        return vacancyResponses.map(new Function<VacancyResponse, VacancyResponseResponseForAdmin>() {
            @Override
            public VacancyResponseResponseForAdmin apply(VacancyResponse vacancyResponse) {
                return modelToDto(vacancyResponse);
            }
        });
    }

    public VacancyResponse dtoToModel(VacancyResponseRequest vacancyResponseRequest) {
        return mapper.map(vacancyResponseRequest, VacancyResponse.class);
    }

    public VacancyResponse dtoToModel(VacancyResponseUpdateRequest vacancyResponseUpdateRequest) {
        return mapper.map(vacancyResponseUpdateRequest, VacancyResponse.class);
    }

    public VacancyResponse dtoToModel(VacancyResponseResponseForAdmin response) {
        return mapper.map(response, VacancyResponse.class);
    }

    public List<VacancyResponse> dtoToModel(List<VacancyResponseResponseForAdmin> responses) {
        return responses.stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }

    public VacancyResponseResponseForUser modelToDtoForUser(VacancyResponse vacancyResponse) {
        return mapper.map(vacancyResponse, VacancyResponseResponseForUser.class);
    }

    public Page<VacancyResponseResponseForUser> modelToDtoForUser(Page<VacancyResponse> vacancyResponsePage) {
        return vacancyResponsePage.map(new Function<VacancyResponse, VacancyResponseResponseForUser>() {
            @Override
            public VacancyResponseResponseForUser apply(VacancyResponse vacancyResponse) {
                return modelToDtoForUser(vacancyResponse);
            }
        });
    }
}
