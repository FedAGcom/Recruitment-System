package com.fedag.recruitmentSystem.mapper;

import com.fedag.recruitmentSystem.dto.request.ExperienceRequest;
import com.fedag.recruitmentSystem.dto.request.ExperienceUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.ExperienceResponse;
import com.fedag.recruitmentSystem.model.Experience;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExperienceMapper {

  private final ModelMapper mapper;

  public ExperienceResponse modelToDto(Experience experience) {
    return mapper.map(experience, ExperienceResponse.class);
  }

  public List<ExperienceResponse> modelToDto(List<Experience> experience) {
    return experience
        .stream()
        .map(this::modelToDto)
        .collect(Collectors.toList());
  }

  public Page<ExperienceResponse> modelToDto(Page<Experience> all) {
    return all
        .map(new Function<Experience, ExperienceResponse>() {
          @Override
          public ExperienceResponse apply(Experience entity) {
            return modelToDto(entity);
          }
        });
  }

  public Experience dtoToModel(ExperienceRequest dto) {
    return mapper.map(dto, Experience.class);
  }

  public Experience dtoToModel(ExperienceUpdateRequest dto) {
    return mapper.map(dto, Experience.class);
  }

  public Experience dtoToModel(ExperienceResponse dto) {
    return mapper.map(dto, Experience.class);
  }

  public List<Experience> dtoToModel(List<ExperienceResponse> dto) {
    return dto
        .stream()
        .map(this::dtoToModel)
        .collect(Collectors.toList());
  }
}
