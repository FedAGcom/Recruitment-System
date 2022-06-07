package com.fedag.recruitmentSystem.service;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.fedag.recruitmentSystem.model.Exam;
import com.fedag.recruitmentSystem.repository.ExamRepository;
import java.util.Optional;
import com.fedag.recruitmentSystem.service.impl.ExamServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class ExamServiceTest {

    @Mock
    private ExamRepository examRepository;

    @InjectMocks
    private ExamServiceImpl examService;

    @Test
    void testGetAllExams(){
        examService.getAllExams();
        verify(examRepository).findAll();
    }

    @Test
    void testGetAllExamsPageable(){
        Pageable pageable = Mockito.any(Pageable.class);
        examService.getAllExams(pageable);
        verify(examRepository).findAll(pageable);
    }

    @Test
    void testGetExamsById(){
        when(examRepository.findById(anyLong())).thenReturn(Optional.of(new Exam()));
        examService.findById(anyLong());
        verify(examRepository).findById(anyLong());
    }

    @Test
    void testExamSave(){
//        Exam exam = new Exam();
//        examService.save(exam);
//        verify(examRepository).save(exam);
    }

    @Test
    void testDeleteExamById(){
        examService.deleteById(anyLong());
        verify(examRepository).deleteById(anyLong());
    }

}

