package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.dto.response.admin_response.VacancyResponseResponseForAdmin;
import com.fedag.recruitmentSystem.dto.response.user_response.VacancyResponseResponseForUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VacancyResponseService<T, S, U> extends AbstractServiceInterface<T, S, U> {

    List<T> getAllVacanciesResponses();

    Page<T> getAllVacanciesResponses(Pageable pageable);

    VacancyResponseResponseForUser findByIdForUser(Long id);

    Page<VacancyResponseResponseForAdmin> getAllVacanciesResponsesByUserId(
            Pageable pageable, Long id);

    Page<VacancyResponseResponseForUser> getAllVacanciesResponsesByUserIdForUser(
            Pageable pageable, Long id);
}
