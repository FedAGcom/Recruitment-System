package com.fedag.recruitmentSystem.mapper;

import com.fedag.recruitmentSystem.dto.ExamRequest;
import com.fedag.recruitmentSystem.dto.ExamResponse;
import com.fedag.recruitmentSystem.model.Exam;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExamMapper {

  private final ModelMapper mapper;

  public ExamResponse modelToDto(Exam exam) {
    return mapper.map(exam, ExamResponse.class);
  }

  public List<ExamResponse> modelToDto(List<Exam> exam) {
    return exam
        .stream()
        .map(this::modelToDto)
        .collect(Collectors.toList());
  }

  public Page<ExamResponse> modelToDto(Page<Exam> examPage) {
    return examPage
        .map(new Function<Exam, ExamResponse>() {
          @Override
          public ExamResponse apply(Exam entity) {
            return modelToDto(entity);
          }
        });
  }

  public Exam dtoToModel(ExamRequest dto) {
    return mapper.map(dto, Exam.class);
  }

  public Exam dtoToModel(ExamResponse dto) {
    return mapper.map(dto, Exam.class);
  }

  public List<Exam> dtoToModel(List<ExamResponse> dto) {
    return dto
        .stream()
        .map(this::dtoToModel)
        .collect(Collectors.toList());
  }
}
