package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.dto.request.TestResultRequest;
import com.fedag.recruitmentSystem.dto.request.TestResultUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.TestResultResponse;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.mapper.TestResultMapper;
import com.fedag.recruitmentSystem.model.TestResult;
import com.fedag.recruitmentSystem.repository.TestResultRepository;
import com.fedag.recruitmentSystem.service.TestResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TestResultServiceImpl implements TestResultService<TestResultResponse, TestResultRequest, TestResultUpdateRequest> {

    private final TestResultRepository testResultRepository;
    private final TestResultMapper testResultMapper;

    @Override
    public TestResultResponse findById(Long id) {
        TestResult test = testResultRepository
                .findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("Test with id: " + id + " not found!")
                );
        return testResultMapper.modelToDto(test);
    }

    @Override
    public void save(TestResultRequest element) {
        TestResult test = testResultMapper.dtoToModel(element);
        testResultRepository.save(test);
    }

    @Override
    public void update(TestResultUpdateRequest element) {
        TestResult test = testResultMapper.dtoToModel(element);
        testResultRepository.save(test);
    }

    @Override
    public void deleteById(Long id) {
        testResultRepository.deleteById(id);
    }

    @Override
    public List<TestResultResponse> getAllTest() {
        return testResultMapper.modelToDto(testResultRepository.findAll());
    }

    @Override
    public Page<TestResultResponse> getAllTest(Pageable pageable) {
        return testResultMapper.modelToDto(testResultRepository.findAll(pageable));
    }
}
