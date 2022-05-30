package com.fedag.recruitmentSystem.Service.examService;

import com.fedag.recruitmentSystem.Dao.ExamRepository;
import com.fedag.recruitmentSystem.Dao.UserRepository;
import com.fedag.recruitmentSystem.model.Exam;
import com.fedag.recruitmentSystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    ExamRepository examRepository;
    
    @Override
    public List<Exam> findAllExams() {
        return examRepository.findAll();
    }

    @Override
    public Exam findExamById(Long id) {
        Exam exam = null;
        Optional<Exam> examOptional = examRepository.findById(id);
        if(examOptional.isPresent()){
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
