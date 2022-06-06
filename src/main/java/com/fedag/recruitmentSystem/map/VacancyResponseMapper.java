package com.fedag.recruitmentSystem.map;

import com.fedag.recruitmentSystem.dto.VacancyResponseResponse;
import com.fedag.recruitmentSystem.model.VacancyResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class VacancyResponseMapper {

    public VacancyResponseResponse modelToDto(VacancyResponse vacancyResponse) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(vacancyResponse, VacancyResponseResponse.class);
    }

    public List<VacancyResponseResponse> modelToDto(List<VacancyResponse> vacancyResponses) {
        return vacancyResponses.stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    public Page<VacancyResponseResponse> modelToDto(Page<VacancyResponse> vacancyResponses) {
        return vacancyResponses.map(new Function<VacancyResponse, VacancyResponseResponse>() {
            @Override
            public VacancyResponseResponse apply(VacancyResponse vacancyResponse) {
                return modelToDto(vacancyResponse);
            }
        });
    }

    public VacancyResponse dtoToModel(VacancyResponseResponse response) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(response, VacancyResponse.class);
    }

    public List<VacancyResponse> dtoToModel(List<VacancyResponseResponse> responses) {
        return responses.stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }
}
