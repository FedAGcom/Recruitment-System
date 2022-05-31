package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.dao.ResumeRepository;
import com.fedag.recruitmentSystem.exceptions.NotFoundException;
import com.fedag.recruitmentSystem.model.Resume;
import com.fedag.recruitmentSystem.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;

    @Override
    public List<Resume> findAllResumes() {
        return resumeRepository.findAll();
    }

    @Override
    public Page<Resume> findAllResumes(Pageable pageable) {
        return resumeRepository.findAll(pageable);
    }

    @Override
    public Resume findResume(Long id) {
        return resumeRepository.findById(id)
          .orElseThrow(()-> new NotFoundException("Resume record with id: " + id + " not found"));
    }

    @Override
    public Resume saveResume(Resume resume) {
        return resumeRepository.save(resume);
    }

    @Override
    public void deleteResume(Long id) {
        resumeRepository.findById(id)
           .orElseThrow(()-> new NotFoundException("Resume record with id: " + id + " not found"));
        resumeRepository.deleteById(id);
    }
}
