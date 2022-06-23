package com.fedag.recruitmentSystem.service.impl;

import com.fedag.recruitmentSystem.dto.request.EducationRequest;
import com.fedag.recruitmentSystem.dto.request.EducationUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.EducationResponseForAdmin;
import com.fedag.recruitmentSystem.dto.response.user_response.EducationResponseForUser;
import com.fedag.recruitmentSystem.exception.ObjectNotFoundException;
import com.fedag.recruitmentSystem.mapper.EducationMapper;
import com.fedag.recruitmentSystem.model.Education;
import com.fedag.recruitmentSystem.model.Exam;
import com.fedag.recruitmentSystem.model.User;
import com.fedag.recruitmentSystem.repository.EducationRepository;
import com.fedag.recruitmentSystem.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EducationServiceImpl implements EducationService<EducationResponseForAdmin,
        EducationRequest, EducationUpdateRequest> {

    private final EducationRepository educationRepository;
    private final EducationMapper educationMapper;

    @Override
    public List<EducationResponseForAdmin> getAllEducation() {
        return educationMapper.modelToDto(educationRepository.findAll());
    }

    @Override
    public Page<EducationResponseForAdmin> getAllEducation(Pageable pageable) {
        return educationMapper.modelToDto(educationRepository.findAll(pageable));
    }

    @Override
    public EducationResponseForAdmin findById(Long id) {
        Education education = educationRepository
                .findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("Education with id: " + id + " not found!")
                );
        return educationMapper.modelToDto(education);
    }

    @Override
    public void save(EducationRequest element) {
//        Education education = educationMapper.dtoToModel(element);
//        Optional<User> user = Optional.ofNullable(education.getUser());
//        user.ifPresent(u -> {
//            if(u.getId()!=null)
//                education.setUser(u);
//        });
//        educationRepository.save(education);

        Education education = educationMapper.dtoToModel(element);
        educationRepository.save(education);
    }

    @Override
    public void update(EducationUpdateRequest element) {
        Education education = educationMapper.dtoToModel(element);
        Optional<User> user = Optional.ofNullable(education.getUser());
        user.ifPresent(u -> {
            if(u.getId()!=null)
                education.setUser(u);
        });
        educationRepository.save(education);
    }

    @Override
    public void deleteById(Long id) {
        educationRepository.deleteById(id);
    }

    @Override
    public EducationResponseForUser findByIdForUser(Long id) {
        Education education = educationRepository
                .findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException("Education with id: " + id + " not found!")
                );
        return educationMapper.modelToDtoForUser(education);
    }
}
