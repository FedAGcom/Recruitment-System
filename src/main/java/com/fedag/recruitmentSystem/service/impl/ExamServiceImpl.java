package com.fedag.recruitmentSystem.service.impl;


import com.fedag.recruitmentSystem.dto.request.ExamRequest;
import com.fedag.recruitmentSystem.dto.request.ExamUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.ExamResponseForAdmin;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.mapper.ExamMapper;
import com.fedag.recruitmentSystem.model.Exam;
import com.fedag.recruitmentSystem.repository.ExamRepository;
import com.fedag.recruitmentSystem.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService<ExamResponseForAdmin,
        ExamRequest, ExamUpdateRequest> {

    private final ExamRepository examRepository;
    private final ExamMapper examMapper;

    @Override
    public List<ExamResponseForAdmin> getAllExams() {
        return examMapper.modelToDto(examRepository.findAll());
    }

    @Override
    public Page<ExamResponseForAdmin> getAllExams(Pageable pageable) {
        return examMapper.modelToDto(examRepository.findAll(pageable));
    }

    @Override
    public ExamResponseForAdmin findById(Long id) {
        Exam exam = examRepository
                .findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("Exam with id: " + id + " not found")
                );
        return examMapper.modelToDto(exam);
    }

    @Override
    public void save(ExamRequest element) {
        Exam exam = examMapper.dtoToModel(element);
        examRepository.save(exam);
    }

    @Override
    public void update(ExamUpdateRequest element) {
        Exam exam = examMapper.dtoToModel(element);
        examRepository.save(exam);
    }

    @Override
    public void deleteById(Long id) {
        examRepository.deleteById(id);
    }
}
