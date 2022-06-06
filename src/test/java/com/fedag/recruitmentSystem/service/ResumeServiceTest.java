package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.enums.ResumeStatus;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.model.Experience;
import com.fedag.recruitmentSystem.model.Resume;
import com.fedag.recruitmentSystem.domain.entity.Skill;
import com.fedag.recruitmentSystem.model.User;
import com.fedag.recruitmentSystem.repository.ResumeRepository;
import com.fedag.recruitmentSystem.service.impl.ResumeServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ResumeServiceTest {
    @Mock
    private ResumeRepository resumeRepository;

    private ResumeServiceImpl resumeService;

    private AutoCloseable autoClosable;

    @BeforeEach
    void setUp() {
        autoClosable = MockitoAnnotations.openMocks(this);
        resumeService = new ResumeServiceImpl(resumeRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoClosable.close();
    }

    @Test
    void itShouldSaveNewResume() {
        Long resumeId = 1L;
        Resume mockResume = getTestResume(1L, 1L, resumeId);
        BDDMockito.given(resumeRepository.save(mockResume)).willReturn(mockResume);
        assertDoesNotThrow(() -> resumeService.save(mockResume));
    }

    @Test
    void itShouldFindResumeById() {
        Long resumeId = 1L;
        Resume mockResume = getTestResume(1L, 1L, resumeId);
        BDDMockito.given(resumeRepository.findById(resumeId)).willReturn(java.util.Optional.of(mockResume));
        Resume resultResume = resumeService.findById(resumeId);
        assertThat(resultResume)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(mockResume);
    }

    @Test
    void itShouldThrowExWhenFindResumeById() {
        Long resumeId = 1L;
        Optional<Resume> res = Optional.empty();
        BDDMockito.given(resumeRepository.findById(resumeId)).willReturn(res);
        Exception exception = assertThrows(ObjectNotFoundException.class, () -> resumeService.findById(resumeId));
        String expectedMessage = "Resume with id: " + resumeId + " not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @ParameterizedTest(name = "test {index}: expected resume names = {1} resume list data = {0}")
    @MethodSource("dataForTest")
    void itShouldFindAllResumes(List<Resume> resumes, List<String> expected) {
        BDDMockito.given(resumeRepository.findAll()).willReturn(resumes);
        List<Resume> actualPages = resumeService.getAllResumes();
        List<String> resumeNames = actualPages.stream()
                .map(Resume::getResumeName)
                .collect(Collectors.toList());
        assertThat(resumeNames)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @ParameterizedTest(name = "test {index}: expected resume names = {1} resume list data = {0}")
    @MethodSource("dataForTest")
    void itShouldFindPagebleResumes(List<Resume> resumes, List<String> expected) {
        int limit = 5;
        Page<Resume> mockedPages = new PageImpl<>(resumes);
        BDDMockito.given(resumeRepository.findAll(PageRequest.of(0, limit))).willReturn(mockedPages);
        Page<Resume> actualPages = resumeService.getAllResumes(PageRequest.of(0, limit));
        List<String> resumeNames = actualPages.get()
                .map(Resume::getResumeName)
                .collect(Collectors.toList());
        assertThat(resumeNames)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void itShouldDeleteNewResume() {
        Long resumeId = 1L;
        Mockito.doNothing().when(resumeRepository).deleteById(resumeId);
        assertDoesNotThrow(() -> resumeService.deleteById(resumeId));
    }

    static Resume getTestResume(Long userId, Long experienceId, Long resumeId) {
        User user = new User(userId, "Ivan", "Petrov", "Ivan@gmail.com", LocalDateTime.now().minusYears(30), null, null, null, null, null);
        List<Experience> experiences = new ArrayList<>();
        List<Skill> skills = new ArrayList<>();
        experiences.add(new Experience(experienceId,"Java developing", LocalDateTime.now().minusYears(2), LocalDateTime.now(), null));
        return new Resume(resumeId, "Ivan CV", ResumeStatus.ACTIVE, experiences, skills, user);
    }

    static Stream<Arguments> dataForTest() {
        List<Resume> resumesArrOne = List.of(
                getTestResume(1L, 1L, 1L),
                getTestResume(2L, 2L, 2L),
                getTestResume(3L, 3L, 3L)
        );

        List<Resume> resumesArrTwo = List.of(
                getTestResume(1L, 1L, 1L),
                getTestResume(2L, 2L, 2L),
                getTestResume(3L, 3L, 3L)
        );

        List<String> expectedOne = List.of("Ivan CV", "Ivan CV", "Ivan CV");
        List<String> expectedTwo = List.of("Ivan CV", "Ivan CV", "Ivan CV");

        return Stream.of(
                Arguments.of(resumesArrOne, expectedOne),
                Arguments.of(resumesArrTwo, expectedTwo)
        );
    }
}