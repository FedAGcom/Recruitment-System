package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.dto.request.ResumeRequest;
import com.fedag.recruitmentSystem.dto.request.ResumeUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.ResumeResponse;
import com.fedag.recruitmentSystem.model.Resume;
import com.fedag.recruitmentSystem.service.impl.ResumeServiceImpl;
import com.fedag.recruitmentSystem.utils.TestDataProvider;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.List;
import java.util.stream.Stream;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ResumeController.class)
class ResumeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ResumeController controller;

    @MockBean
    private ResumeServiceImpl resumeService;

    @Test
    public void contextLoads() {
        Assertions.assertThat(controller).isNotNull();
    }

    @ParameterizedTest(name = "test {index}: students from service = {0} expected = {1}")
    @MethodSource("dataForTest")
    void getAllResumes(List<ResumeResponse> resumes, List<String> expected) throws Exception {
        int limit = 5;
        Page<ResumeResponse> mockedPages = new PageImpl<>(resumes);
        Mockito.when(resumeService.getAllResumes(PageRequest.of(0, limit))).thenReturn(mockedPages);
        mockMvc.perform(get("/api/resumes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].resumeName", Matchers.is(expected.get(0))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].resumeName", Matchers.is(expected.get(1))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[2].resumeName", Matchers.is(expected.get(2))));
    }

    @Test
    void getById() throws Exception {
        Long resumeId = 1L;
        ResumeResponse mockResume = TestDataProvider.getTestResumeResponse(TestDataProvider.getTestResume(1L, 1L, 1L));
        Mockito.when(resumeService.findById(resumeId)).thenReturn(mockResume);
        mockMvc.perform(get("/api/resumes/" + resumeId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.resumeName", Matchers.is(mockResume.getResumeName())));
    }

    @Test
    void deleteById() throws Exception {
        Long resumeId = 1L;
        Mockito.doNothing().when(resumeService).deleteById(resumeId);
        mockMvc.perform(delete("/api/resumes/" + resumeId))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void addVacancy() throws Exception {
        ResumeRequest mockResume = TestDataProvider.getTestResumeRequest(TestDataProvider.getTestResume(1L, 1L, 1L));
        Mockito.doNothing().when(resumeService).save(mockResume);
        mockMvc.perform(post("/api/resumes/", mockResume)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"resumeName\":\"Ivan CV\",\"status\":\"ACTIVE\",\"experiences\":[{\"id\":1,\"description\":\"Java developing\",\"endDate\":\"2022-06-02T15:40:32.0247436\",\"startDate\":\"2020-06-02T15:40:32.0247436\"}]}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateVacancy() throws Exception {
        ResumeUpdateRequest mockResume = TestDataProvider.getTestResumeUpdateRequest(TestDataProvider.getTestResume(1L, 1L, 1L));
        Mockito.doNothing().when(resumeService).update(mockResume);
        mockMvc.perform(put("/api/resumes/"+mockResume.getId(), mockResume)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"resumeName\":\"Ivan CV\",\"status\":\"ACTIVE\",\"experiences\":[{\"id\":1,\"description\":\"Java developing\",\"startDate\":\"2020-06-02T14:29:10.3825003\",\"endDate\":\"2022-06-02T14:29:10.3825003\"}],\"skills\":[],\"user\":{\"id\":1,\"firstname\":\"Ivan\",\"lastname\":\"Petrov\",\"email\":\"Ivan@gmail.com\",\"birthday\":\"1992-06-02T14:29:10.3814968\"}}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    static Stream<Arguments> dataForTest() {
        List<Resume> resumesArrOne = List.of(
                TestDataProvider.getTestResume(1L, 1L, 1L),
                TestDataProvider.getTestResume(2L, 2L, 2L),
                TestDataProvider.getTestResume(3L, 3L, 3L)
        );

        List<Resume> resumesArrTwo = List.of(
                TestDataProvider.getTestResume(1L, 1L, 1L),
                TestDataProvider.getTestResume(2L, 2L, 2L),
                TestDataProvider.getTestResume(3L, 3L, 3L)
        );

        List<String> expectedOne = List.of("Ivan CV", "Ivan CV", "Ivan CV");
        List<String> expectedTwo = List.of("Ivan CV", "Ivan CV", "Ivan CV");

        return Stream.of(
                Arguments.of(resumesArrOne, expectedOne),
                Arguments.of(resumesArrTwo, expectedTwo)
        );
    }
}