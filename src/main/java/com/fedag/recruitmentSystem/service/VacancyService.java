package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.dto.response.admin_response.VacancyResponseForAdmin;
import com.fedag.recruitmentSystem.dto.response.user_response.VacancyResponseForUser;
import com.fedag.recruitmentSystem.model.CustomCalendarEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VacancyService<T, S, U> extends AbstractServiceInterface<T, S, U> {

    List<T> getAllVacancies();

    Page<T> getAllVacancies(Pageable pageable);

    List<T> findByDateCreated();

    List<String> findCalendarEvents(String calendarId);

    void signForEvent(String calendarId, String eventSummary);

    void createCalendarEvent(String calendarId, CustomCalendarEvent customCalendarEvent);

    Page<VacancyResponseForUser> getAllVacanciesForUser(Pageable pageable);

    VacancyResponseForUser findByIdForUser(Long id);

    List<VacancyResponseForUser> findByDateCreatedForUser();

    Page<VacancyResponseForAdmin> getAllVacanciesByCompanyId(Pageable pageable, Long id);

    Page<VacancyResponseForUser> getAllVacanciesByCompanyIdForUser(Pageable pageable, Long id);
}
