package com.fedag.recruitmentSystem.map;

import com.fedag.recruitmentSystem.dto.ExamResponse;
import com.fedag.recruitmentSystem.model.Exam;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ExamMapper {

    public ExamResponse modelToDto(Exam exam) {
        ModelMapper mapper = new ModelMapper();
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

    public Exam dtoToModel(ExamResponse dto) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(dto, Exam.class);

    }

    public List<Exam> dtoToModel(List<ExamResponse> dto) {
        return dto
                .stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }
}
