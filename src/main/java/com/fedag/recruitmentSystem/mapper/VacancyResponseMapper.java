package com.fedag.recruitmentSystem.mapper;

import com.fedag.recruitmentSystem.dto.request.VacancyResponseRequest;
import com.fedag.recruitmentSystem.dto.request.VacancyResponseUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.VacancyResponseResponse;
import com.fedag.recruitmentSystem.model.VacancyResponse;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VacancyResponseMapper {

  private final ModelMapper mapper;

  public VacancyResponseResponse modelToDto(VacancyResponse vacancyResponse) {
    return mapper.map(vacancyResponse, VacancyResponseResponse.class);
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

  public VacancyResponse dtoToModel(VacancyResponseRequest vacancyResponseRequest) {
    return mapper.map(vacancyResponseRequest, VacancyResponse.class);
  }

  public VacancyResponse dtoToModel(VacancyResponseUpdateRequest vacancyResponseUpdateRequest) {
    return mapper.map(vacancyResponseUpdateRequest, VacancyResponse.class);
  }

  public VacancyResponse dtoToModel(VacancyResponseResponse response) {
    return mapper.map(response, VacancyResponse.class);
  }

  public List<VacancyResponse> dtoToModel(List<VacancyResponseResponse> responses) {
    return responses.stream()
        .map(this::dtoToModel)
        .collect(Collectors.toList());
  }
}
