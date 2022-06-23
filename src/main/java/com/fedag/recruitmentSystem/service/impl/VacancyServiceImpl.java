package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.dto.request.VacancyRequest;
import com.fedag.recruitmentSystem.dto.request.VacancyUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.VacancyResponseForAdmin;
import com.fedag.recruitmentSystem.dto.response.user_response.VacancyResponseForUser;
import com.fedag.recruitmentSystem.exception.EventAttendeeAlreadyExists;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.mapper.VacancyMapper;
import com.fedag.recruitmentSystem.model.CustomCalendarEvent;
import com.fedag.recruitmentSystem.repository.VacancyRepository;
import com.fedag.recruitmentSystem.service.VacancyService;
import com.fedag.recruitmentSystem.service.utils.GoogleApi;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService<VacancyResponseForAdmin, VacancyRequest, VacancyUpdateRequest> {

    private final UserServiceImpl userService;
    private final VacancyRepository vacancyRepository;
    private final VacancyMapper vacancyMapper;

    @Override
    public List<VacancyResponseForAdmin> getAllVacancies() {
        return vacancyRepository
                .findAll()
                .stream()
                .map(vacancyMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<VacancyResponseForAdmin> getAllVacancies(Pageable pageable) {
        return vacancyRepository
                .findAll(pageable)
                .map(vacancyMapper::toDto);
    }

    @Override
    public VacancyResponseForAdmin findById(Long id) {
        return vacancyMapper.toDto(vacancyRepository
                .findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("Vacancy with id: " + id + " not found")
                ));
    }


    public List<VacancyResponseForAdmin> findByDateCreated() {
        return vacancyMapper.toDto(vacancyRepository.findByDateCreated());
    }

    @Override
    public void save(VacancyRequest element) {
        vacancyRepository.save(vacancyMapper.toEntity(element));
    }

    @Override
    public void update(VacancyUpdateRequest element) {
        vacancyRepository.save(vacancyMapper.toEntity(element));
    }

    @Override
    public void deleteById(Long id) {
        vacancyRepository.deleteById(id);
    }

    @Override
    @SneakyThrows
    public List<String> findCalendarEvents(String calendarId) {

        Calendar calendar = GoogleApi.getCalendar();
        List<Event> event = calendar.events().list(calendarId).execute().getItems();

        return event.stream()
                .map(e -> "Header: " + e.getSummary() + " | Start time: " + e.getStart().getDateTime() + " | Description:" + e.getDescription())
                .collect(Collectors.toList());
    }

    @Override
    @SneakyThrows
    public void signForEvent(String calendarId, String eventSummary) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = userService.findByEmail(authentication.getName()).getEmail();

        Calendar calendar = GoogleApi.getCalendar();
        List<Event> events = calendar.events().list(calendarId).execute().getItems();
        if (events.stream().anyMatch(e -> e.getSummary().equals(eventSummary))) {
            Event event = events.stream().filter(e -> e.getSummary().equals(eventSummary)).findFirst().get();

            if (Optional.ofNullable(event.getAttendees()).isPresent()) {
                throw new EventAttendeeAlreadyExists();
            }

            EventAttendee eventAttendee = new EventAttendee();
            eventAttendee.setEmail(userEmail);

            event.setAttendees(Collections.singletonList(eventAttendee));

            Event eventUpdate = calendar.events().update(calendarId, event.getId(), event).execute();
        }
    }

    @Override
    @SneakyThrows
    public void createCalendarEvent(String calendarId, CustomCalendarEvent customCalendarEvent) {

        Calendar calendar = GoogleApi.getCalendar();
        Event event = new Event();

        event.setSummary(customCalendarEvent.getSummary());
        event.setDescription(customCalendarEvent.getDescription());

        DateTime eventStartTime = new DateTime(customCalendarEvent.getEventStartTime());
        event.setStart(new EventDateTime().setDateTime(eventStartTime));

        DateTime eventEndTime = new DateTime(customCalendarEvent.getEventEndTime());
        event.setEnd(new EventDateTime().setDateTime(eventEndTime));

        Event eventCreate = calendar.events().insert(calendarId, event).execute();
    }

    @Override
    public Page<VacancyResponseForUser> getAllVacanciesForUser(Pageable pageable) {
        return vacancyRepository
                .findAll(pageable)
                .map(vacancyMapper::toDtoForUser);
    }

    @Override
    public VacancyResponseForUser findByIdForUser(Long id) {
        return vacancyMapper.toDtoForUser(vacancyRepository
                .findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("Vacancy with id: " + id + " not found")
                ));
    }

    @Override
    public List<VacancyResponseForUser> findByDateCreatedForUser() {
        return vacancyMapper.toDtoForUser(vacancyRepository.findByDateCreated());
    }

    @Override
    public Page<VacancyResponseForAdmin> getAllVacanciesByCompanyId(Pageable pageable, Long id) {
        return vacancyRepository.findByCompanyId(pageable, id)
                .map(vacancyMapper::toDto);
    }

    @Override
    public Page<VacancyResponseForUser> getAllVacanciesByCompanyIdForUser(Pageable pageable, Long id) {
        return vacancyRepository.findByCompanyId(pageable, id)
                .map(vacancyMapper::toDtoForUser);
    }

}
