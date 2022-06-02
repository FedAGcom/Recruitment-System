package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.model.VacancyResponse;
import com.fedag.recruitmentSystem.repository.VacancyResponseRepository;
import com.fedag.recruitmentSystem.service.impl.VacancyResponseServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VacancyResponseServiceTest {
    @Mock
    VacancyResponseRepository vacancyResponseRepository;

    @InjectMocks
    VacancyResponseServiceImpl vacancyResponseService;

    @Test
    void testGetAllVacancyResponse() {
        vacancyResponseService.getAllVacanciesResponses();
        verify(vacancyResponseRepository).findAll();
    }

    @Test
    void testGetAllVacancyResponsePageable() {
        Pageable pageable = Mockito.any(Pageable.class);
        vacancyResponseService.getAllVacanciesResponses(pageable);
        verify(vacancyResponseRepository).findAll(pageable);
    }

    @Test
    void testFindById(){
        when(vacancyResponseRepository.findById(anyLong())).thenReturn(Optional.of(new VacancyResponse()));
        vacancyResponseService.findById(anyLong());
        verify(vacancyResponseRepository).findById(anyLong());
    }

    @Test
    void testSave() {
        VacancyResponse vacancyResponse = new VacancyResponse();
        vacancyResponseService.save(vacancyResponse);
        verify(vacancyResponseRepository).save(vacancyResponse);
    }

    @Test
    void testDeleteById() {
        vacancyResponseService.deleteById(anyLong());
        verify(vacancyResponseRepository).deleteById(anyLong());
    }
}
