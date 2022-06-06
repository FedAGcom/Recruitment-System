package com.fedag.recruitmentSystem.map;

import com.fedag.recruitmentSystem.dto.ExperienceResponse;
import com.fedag.recruitmentSystem.model.Experience;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ExperienceMapper {
    public ExperienceResponse modelToDto(Experience experience) {
        ModelMapper mapper = new ModelMapper();
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

    public Experience dtoToModel(ExperienceResponse dto) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(dto, Experience.class);

    }

    public List<Experience> dtoToModel(List<ExperienceResponse> dto) {
        return dto
                .stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }
}
