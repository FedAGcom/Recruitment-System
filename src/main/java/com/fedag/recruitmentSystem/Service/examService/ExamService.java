package com.fedag.recruitmentSystem.Service.examService;

import com.fedag.recruitmentSystem.model.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;


public interface ExamService {

    List<Exam> findAllExams();

    Page<Exam> findAllExams(Pageable pageable);

    Exam findExamById(Long id);

    void saveExam(Exam exam);

    void deleteExamById(Long id);
}
