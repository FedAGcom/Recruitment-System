package com.fedag.recruitmentSystem.mapper;

import com.fedag.recruitmentSystem.dto.request.ResumeRequest;
import com.fedag.recruitmentSystem.dto.request.ResumeUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.ResumeResponse;
import com.fedag.recruitmentSystem.model.Resume;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResumeMapper {

  private final ModelMapper mapper;

  public ResumeResponse modelToDto(Resume resume) {
    return mapper.map(resume, ResumeResponse.class);
  }

  public ResumeRequest modelToRequestDto(Resume resume) {
    return mapper.map(resume, ResumeRequest.class);
  }

  public ResumeUpdateRequest modelToUpdateRequestDto(Resume resume) {
    return mapper.map(resume, ResumeUpdateRequest.class);
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

  public Resume dtoToModel(ResumeRequest dto) {
    return mapper.map(dto, Resume.class);
  }

  public Resume dtoToModel(ResumeUpdateRequest dto) {
    return mapper.map(dto, Resume.class);
  }

  public Resume dtoToModel(ResumeResponse dto) {
    return mapper.map(dto, Resume.class);

  }

  public List<Resume> dtoToModel(List<ResumeResponse> dto) {
    return dto
        .stream()
        .map(this::dtoToModel)
        .collect(Collectors.toList());
  }
}
