package com.fedag.recruitmentSystem.utils;

import com.fedag.recruitmentSystem.dto.request.ResumeRequest;
import com.fedag.recruitmentSystem.dto.request.ResumeUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.ExperienceShortResponse;
import com.fedag.recruitmentSystem.dto.response.ResumeResponse;
import com.fedag.recruitmentSystem.dto.response.UserResponse;
import com.fedag.recruitmentSystem.enums.ActiveStatus;
import com.fedag.recruitmentSystem.enums.Role;
import com.fedag.recruitmentSystem.model.Experience;
import com.fedag.recruitmentSystem.model.Resume;
import com.fedag.recruitmentSystem.model.Skill;
import com.fedag.recruitmentSystem.model.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestDataProvider {
  
    public static Resume getTestResume(Long userId, Long experienceId, Long resumeId) {
        User user = new User(userId, "Ivan", "Petrov", "Ivan@gmail.com", LocalDateTime.now().minusYears(30), Role.USER, "some pass", "some activation code", ActiveStatus.ACTIVE, null,null, null, null, null, null);
        List<Experience> experiences = new ArrayList<>();
        List<Skill> skills = new ArrayList<>();
        experiences.add(new Experience(experienceId,"Java developing", LocalDateTime.now().minusYears(2), LocalDateTime.now(), null));
        return new Resume(resumeId, "Ivan CV", ActiveStatus.ACTIVE, LocalDateTime.now(), experiences, skills, user);
    }

    public static ResumeResponse getTestResumeResponse(Resume resume) {
        ResumeResponse resumeResponse = new ResumeResponse();
        resumeResponse.setId(resume.getId());
        resumeResponse.setResumeName(resume.getResumeName());
        resumeResponse.setDateCreated(resume.getDateCreated());
        List<ExperienceShortResponse> experiences = new ArrayList<>();
        experiences.add(new ExperienceShortResponse(resume.getExperiences().get(0).getId(), "Java developing", LocalDateTime.now().minusYears(2), LocalDateTime.now()));
        resumeResponse.setExperiences(experiences);
        resumeResponse.setUser(new UserResponse(resume.getUser().getId(),
                resume.getUser().getFirstname(),
                resume.getUser().getLastname(),
                resume.getUser().getEmail(),
                resume.getUser().getBirthday(),
                resume.getUser().getActivationCode(),
                resume.getUser().getActiveStatus()
        ));
        return resumeResponse;
    }

    public static ResumeRequest getTestResumeRequest(Resume resume) {
        ResumeRequest resumeRequest = new ResumeRequest();
        resumeRequest.setId(resume.getId());
        resumeRequest.setResumeName(resume.getResumeName());
        resumeRequest.setDateCreated(resume.getDateCreated());
        List<ExperienceShortResponse> experiences = new ArrayList<>();
        experiences.add(new ExperienceShortResponse(resume.getExperiences().get(0).getId(), "Java developing", LocalDateTime.now().minusYears(2), LocalDateTime.now()));
        resumeRequest.setExperiences(experiences);
        resumeRequest.setUser(new UserResponse(resume.getUser().getId(),
                resume.getUser().getFirstname(),
                resume.getUser().getLastname(),
                resume.getUser().getEmail(),
                resume.getUser().getBirthday(),
                resume.getUser().getActivationCode(),
                resume.getUser().getActiveStatus()
        ));
        return resumeRequest;
    }

    public static ResumeUpdateRequest getTestResumeUpdateRequest(Resume resume) {
        ResumeUpdateRequest resumeUpdateRequest = new ResumeUpdateRequest();
        resumeUpdateRequest.setId(resume.getId());
        resumeUpdateRequest.setResumeName(resume.getResumeName());
        resumeUpdateRequest.setDateCreated(resume.getDateCreated());
        List<ExperienceShortResponse> experiences = new ArrayList<>();
        experiences.add(new ExperienceShortResponse(resume.getExperiences().get(0).getId(), "Java developing", LocalDateTime.now().minusYears(2), LocalDateTime.now()));
        resumeUpdateRequest.setExperiences(experiences);
        resumeUpdateRequest.setUser(new UserResponse(resume.getUser().getId(),
                resume.getUser().getFirstname(),
                resume.getUser().getLastname(),
                resume.getUser().getEmail(),
                resume.getUser().getBirthday(),
                resume.getUser().getActivationCode(),
                resume.getUser().getActiveStatus()
        ));
        return resumeUpdateRequest;
    }
}
