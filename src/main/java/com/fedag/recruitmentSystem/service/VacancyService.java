package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.model.CustomCalendarEvent;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VacancyService<T, S, U> extends AbstractServiceInterface<T, S, U> {

  List<T> getAllVacancies();

  Page<T> getAllVacancies(Pageable pageable);

  List<T> findByDateCreated();

  List<String> findCalendarEvents(String calendarId);

  void signForEvent(String calendarId, String eventSummary);

  void createCalendarEvent(String calendarId, CustomCalendarEvent customCalendarEvent);
}
