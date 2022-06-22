package com.fedag.recruitmentSystem.mapper;

import com.fedag.recruitmentSystem.dto.request.EducationRequest;
import com.fedag.recruitmentSystem.dto.request.EducationUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.EducationResponse;
import com.fedag.recruitmentSystem.model.Education;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EducationMapper {

    private final ModelMapper mapper;

    public EducationResponse modelToDto(Education education) {
        return mapper.map(education, EducationResponse.class);
    }

    public List<EducationResponse> modelToDto(List<Education> educations) {
        return educations
                .stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    public Page<EducationResponse> modelToDto(Page<Education> all) {
        return all
                .map(new Function<Education, EducationResponse>() {
                    @Override
                    public EducationResponse apply(Education education) {
                        return modelToDto(education);
                    }
                });
    }

    public Education dtoToModel(EducationRequest dto) {
        return mapper.map(dto, Education.class);
    }

    public Education dtoToModel(EducationUpdateRequest dto) {
        return mapper.map(dto, Education.class);
    }

    public Education dtoToModel(EducationResponse dto) {
        return mapper.map(dto, Education.class);
    }

    public List<Education> dtoToModel(List<EducationResponse> dto) {
        return dto
                .stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }
}
