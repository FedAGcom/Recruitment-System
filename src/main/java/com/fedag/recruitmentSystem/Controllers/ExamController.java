package com.fedag.recruitmentSystem.Controllers;

import com.fedag.recruitmentSystem.Service.examService.ExamService;
import com.fedag.recruitmentSystem.model.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ExamController {
    @Autowired
    private ExamService examService;

    @GetMapping("/exams")
    public List<Exam> showAllExams(){
        List<Exam> allExams = examService.findAllExams();
        return allExams;
    }

    @GetMapping("/exams/{id}")
    public Exam getExam(@PathVariable Long id){
        Exam exam = examService.findExamById(id);
        return exam;
    }

    @PostMapping("/exams")
    public void addNewExam(@RequestBody Exam exam){
       examService.saveExam(exam);
    }

    @PutMapping("/exams")
    public void updateExam(@RequestBody Exam exam){
        examService.saveExam(exam);
    }

    @DeleteMapping("/{id}")
    public void deleteExam(@PathVariable Long id){
        examService.deleteExamById(id);
    }
}
