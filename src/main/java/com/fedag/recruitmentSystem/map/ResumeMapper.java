package com.fedag.recruitmentSystem.map;

import com.fedag.recruitmentSystem.dto.ResumeResponse;
import com.fedag.recruitmentSystem.model.Resume;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ResumeMapper {
    public ResumeResponse modelToDto(Resume resume) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(resume, ResumeResponse.class);
    }

    public List<ResumeResponse> modelToDto(List<Resume> user) {
        return user
                .stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    public Page<ResumeResponse> modelToDto(Page<Resume> all) {
        return all
                .map(new Function<Resume, ResumeResponse>() {
                    @Override
                    public ResumeResponse apply(Resume entity) {
                        return modelToDto(entity);
                    }
                });
    }

    public Resume dtoToModel(ResumeResponse dto) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(dto, Resume.class);

    }

    public List<Resume> dtoToModel(List<ResumeResponse> dto) {
        return dto
                .stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }
}
