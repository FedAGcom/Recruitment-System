package com.fedag.recruitmentSystem.service;

import com.fedag.recruitmentSystem.model.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ResumeService {
    List<Resume> findAllResumes();
    Page<Resume> findAllResumes(Pageable pageable);
    Resume findResume(Long id);
    Resume saveResume(Resume resume);
    void deleteResume(Long id);
}
