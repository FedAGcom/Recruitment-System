package com.fedag.recruitmentSystem.mapper;

import com.fedag.recruitmentSystem.dto.request.QuestionRequest;
import com.fedag.recruitmentSystem.dto.request.QuestionUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.QuestionResponse;
import com.fedag.recruitmentSystem.model.Question;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionMapper {

  private final ModelMapper mapper;

  public QuestionResponse modelToDto(Question question) {
    return mapper.map(question, QuestionResponse.class);
  }

  public List<QuestionResponse> modelToDto(List<Question> questions) {
    return questions.stream()
        .map(this::modelToDto)
        .collect(Collectors.toList());
  }

  public Page<QuestionResponse> modelToDto(Page<Question> questions) {
    return questions.map(new Function<Question, QuestionResponse>() {
      @Override
      public QuestionResponse apply(Question question) {
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

  public Question dtoToModel(QuestionResponse response) {
    return mapper.map(response, Question.class);
  }

  public List<Question> dtoToModel(List<QuestionResponse> responses) {
    return responses.stream()
        .map(this::dtoToModel)
        .collect(Collectors.toList());
  }
}
