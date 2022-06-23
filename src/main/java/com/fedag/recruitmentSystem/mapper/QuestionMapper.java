package com.fedag.recruitmentSystem.mapper;

import com.fedag.recruitmentSystem.dto.request.QuestionRequest;
import com.fedag.recruitmentSystem.dto.request.QuestionUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.QuestionResponseForAdmin;
import com.fedag.recruitmentSystem.model.Question;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class QuestionMapper {

    private final ModelMapper mapper;

    public QuestionResponseForAdmin modelToDto(Question question) {
        return mapper.map(question, QuestionResponseForAdmin.class);
    }

    public List<QuestionResponseForAdmin> modelToDto(List<Question> questions) {
        return questions.stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    public Page<QuestionResponseForAdmin> modelToDto(Page<Question> questions) {
        return questions.map(new Function<Question, QuestionResponseForAdmin>() {
            @Override
            public QuestionResponseForAdmin apply(Question question) {
                return modelToDto(question);
            }
        });
    }

    public Question dtoToModel(QuestionRequest dto) {
        return mapper.map(dto, Question.class);
    }

    public Question dtoToModel(QuestionUpdateRequest dto) {
        return mapper.map(dto, Question.class);
    }

    public Question dtoToModel(QuestionResponseForAdmin response) {
        return mapper.map(response, Question.class);
    }

    public List<Question> dtoToModel(List<QuestionResponseForAdmin> responses) {
        return responses.stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }
}
