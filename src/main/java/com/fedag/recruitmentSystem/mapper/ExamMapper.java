package com.fedag.recruitmentSystem.mapper;

import com.fedag.recruitmentSystem.dto.request.ExamRequest;
import com.fedag.recruitmentSystem.dto.request.ExamUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.ExamResponseForAdmin;
import com.fedag.recruitmentSystem.model.Exam;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ExamMapper {

    private final ModelMapper mapper;

    public ExamResponseForAdmin modelToDto(Exam exam) {
        return mapper.map(exam, ExamResponseForAdmin.class);
    }

    public List<ExamResponseForAdmin> modelToDto(List<Exam> exam) {
        return exam
                .stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    public Page<ExamResponseForAdmin> modelToDto(Page<Exam> examPage) {
        return examPage
                .map(new Function<Exam, ExamResponseForAdmin>() {
                    @Override
                    public ExamResponseForAdmin apply(Exam entity) {
                        return modelToDto(entity);
                    }
                });
    }

    public Exam dtoToModel(ExamRequest dto) {
        return mapper.map(dto, Exam.class);
    }

    public Exam dtoToModel(ExamUpdateRequest dto) {
        return mapper.map(dto, Exam.class);
    }

    public Exam dtoToModel(ExamResponseForAdmin dto) {
        return mapper.map(dto, Exam.class);
    }

    public List<Exam> dtoToModel(List<ExamResponseForAdmin> dto) {
        return dto
                .stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }
}
