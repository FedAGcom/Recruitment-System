package com.fedag.recruitmentSystem.Service.examService;

import com.fedag.recruitmentSystem.model.Exam;

import java.util.List;


public interface ExamService {

    List<Exam> findAllExams();


    Exam findExamById(Long id);


    void saveExam(Exam exam);


    void deleteExamById(Long id);
}
