package com.fedag.recruitmentSystem.mapper;

import com.fedag.recruitmentSystem.dto.request.TestResultRequest;
import com.fedag.recruitmentSystem.dto.request.TestResultUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.TestResultResponse;
import com.fedag.recruitmentSystem.model.TestResult;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TestResultMapper {

    private final ModelMapper mapper;

    public TestResultResponse modelToDto(TestResult testResult) {
        return mapper.map(testResult, TestResultResponse.class);
    }

    public TestResultRequest modelToRequestDto(TestResult testResult) {
        return mapper.map(testResult, TestResultRequest.class);
    }

    public TestResultUpdateRequest modelToUpdateRequestDto(TestResult testResult) {
        return mapper.map(testResult, TestResultUpdateRequest.class);
    }

    public List<TestResultResponse> modelToDto(List<TestResult> user) {
        return user
                .stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    public Page<TestResultResponse> modelToDto(Page<TestResult> all) {
        return all
                .map(new Function<TestResult, TestResultResponse>() {
                    @Override
                    public TestResultResponse apply(TestResult entity) {
                        return modelToDto(entity);
                    }
                });
    }

    public TestResult dtoToModel(TestResultRequest dto) {
        return mapper.map(dto, TestResult.class);
    }

    public TestResult dtoToModel(TestResultUpdateRequest dto) {
        return mapper.map(dto, TestResult.class);
    }

    public TestResult dtoToModel(TestResultResponse dto) {
        return mapper.map(dto, TestResult.class);

    }

    public List<TestResult> dtoToModel(List<TestResultResponse> dto) {
        return dto
                .stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }
}
