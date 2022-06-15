package com.fedag.recruitmentSystem.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fedag.recruitmentSystem.dto.request.QuestionRequest;
import com.fedag.recruitmentSystem.dto.request.QuestionUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.QuestionResponse;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.mapper.QuestionMapper;
import com.fedag.recruitmentSystem.model.Question;
import com.fedag.recruitmentSystem.repository.QuestionRepository;
import com.fedag.recruitmentSystem.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService<QuestionResponse, QuestionRequest, QuestionUpdateRequest> {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    private final static String INDEX_NAME = "question";
    private final ObjectMapper mapper = new ObjectMapper();
    private final RestHighLevelClient esClient;


    public QuestionResponse findById(String id) {
        Question question = questionRepository
                .findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("Question not found")
                );
        return questionMapper.modelToDto(question);
    }

    public void save(QuestionRequest element) {
        Question question = questionMapper.dtoToModel(element);
        questionRepository.save(question);
    }

    @Override
    public void update(QuestionUpdateRequest request) {
        Question question = questionMapper.dtoToModel(request);
        questionRepository.save(question);

        IndexRequest indexRequest = new IndexRequest(INDEX_NAME);
        indexRequest.id(request.getId());
        try {
            indexRequest.source(mapper.writeValueAsString(question), XContentType.JSON);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            esClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(String id) {
        questionRepository.deleteById(id);
    }

    @Override
    public List<QuestionResponse> getAllQuestions() {
        return questionMapper.modelToDto(questionRepository.findAll());
    }

    @Override
    public Page<QuestionResponse> getAllQuestions(Pageable pageable) {
        return questionMapper.modelToDto(questionRepository.findAll(pageable));
    }
}

