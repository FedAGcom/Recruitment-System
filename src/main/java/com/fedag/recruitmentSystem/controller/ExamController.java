package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.service.examService.ExamService;
import com.fedag.recruitmentSystem.model.Exam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exams")
public class ExamController {

    private final ExamService examService;

    @GetMapping
    public Page<Exam> showAllExams(@PageableDefault(size = 5) Pageable pageable) {
        return examService.findAllExams(pageable);
    }

    @GetMapping("/{id}")
    public Exam getExam(@PathVariable Long id){
        Exam exam = examService.findExamById(id);
        return exam;
    }

    @PostMapping("/")
    public void addNewExam(@RequestBody Exam exam){
       examService.saveExam(exam);
    }

    @PutMapping("/")
    public void updateExam(@RequestBody Exam exam){
        examService.saveExam(exam);
    }

    @DeleteMapping("/{id}")
    public void deleteExam(@PathVariable Long id){
        examService.deleteExamById(id);
    }
}
