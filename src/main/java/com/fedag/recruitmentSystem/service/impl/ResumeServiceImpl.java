package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.dao.ResumeRepository;
import com.fedag.recruitmentSystem.exceptions.NotFoundException;
import com.fedag.recruitmentSystem.model.Resume;
import com.fedag.recruitmentSystem.service.ResumeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;

    @Override
    public List<Resume> findAllResumes() {
        return resumeRepository.findAll();
    }

    @Override
    public Resume findResume(Long id) {
        return resumeRepository.findById(id)
          .orElseThrow(()-> new NotFoundException("Resume record with id: " + id + " not available"));
    }

    @Override
    public Resume saveResume(Resume resume) {
        return resumeRepository.save(resume);
    }

    @Override
    public void deleteResume(Long id) {
        resumeRepository.deleteById(id);
    }
}
