package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.model.Resume;
import java.util.List;

public interface ResumeService {
    List<Resume> findAllResumes();
    Resume findResume(Long id);
    Resume saveResume(Resume resume);
    void deleteResume(Long id);
}
