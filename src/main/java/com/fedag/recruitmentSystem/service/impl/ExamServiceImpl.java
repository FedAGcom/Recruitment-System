package com.fedag.recruitmentSystem.service.impl;


import com.fedag.recruitmentSystem.model.Exam;
import com.fedag.recruitmentSystem.repository.ExamRepository;
import com.fedag.recruitmentSystem.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;

    @Override
    public List<Exam> findAllExams() {
        return examRepository.findAll();

    }

    public Page<Exam> findAllExams(Pageable pageable) {
        return examRepository.findAll(pageable);
    }

    @Override
    public Exam findExamById(Long id) {
        Exam exam = null;
        Optional<Exam> examOptional = examRepository.findById(id);
        if (examOptional.isPresent()) {
            exam = examOptional.get();
        }
        return exam;
    }

    @Override
    public void saveExam(Exam exam) {
        examRepository.save(exam);
    }

    @Override
    public void deleteExamById(Long id) {
        examRepository.deleteById(id);
    }
}
