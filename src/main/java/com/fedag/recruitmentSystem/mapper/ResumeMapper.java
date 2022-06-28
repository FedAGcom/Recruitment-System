package com.fedag.recruitmentSystem.mapper;

import com.fedag.recruitmentSystem.dto.request.ResumeRequest;
import com.fedag.recruitmentSystem.dto.request.ResumeUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.ResumeResponseForAdmin;
import com.fedag.recruitmentSystem.dto.response.user_response.ResumeResponseForUser;
import com.fedag.recruitmentSystem.model.Resume;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ResumeMapper {

    private final ModelMapper mapper;

    public ResumeResponseForAdmin modelToDto(Resume resume) {
        return mapper.map(resume, ResumeResponseForAdmin.class);
    }

    public ResumeRequest modelToRequestDto(Resume resume) {
        return mapper.map(resume, ResumeRequest.class);
    }

    public ResumeUpdateRequest modelToUpdateRequestDto(Resume resume) {
        return mapper.map(resume, ResumeUpdateRequest.class);
    }

    public List<ResumeResponseForAdmin> modelToDto(List<Resume> users) {
        return users
                .stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    public Page<ResumeResponseForAdmin> modelToDto(Page<Resume> all) {
        return all
                .map(new Function<Resume, ResumeResponseForAdmin>() {
                    @Override
                    public ResumeResponseForAdmin apply(Resume entity) {
                        return modelToDto(entity);
                    }
                });
    }

    public Resume dtoToModel(ResumeRequest dto) {
        return mapper.map(dto, Resume.class);
    }

    public Resume dtoToModel(ResumeUpdateRequest dto) {
        return mapper.map(dto, Resume.class);
    }

    public Resume dtoToModel(ResumeResponseForAdmin dto) {
        return mapper.map(dto, Resume.class);

    }

    public List<Resume> dtoToModel(List<ResumeResponseForAdmin> dto) {
        return dto
                .stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }

    public Page<ResumeResponseForUser> modelToDtoForUser(Page<Resume> resumePage) {
        return resumePage
                .map(new Function<Resume, ResumeResponseForUser>() {
                    @Override
                    public ResumeResponseForUser apply(Resume entity) {
                        return modelToDtoForUser(entity);
                    }
                });
    }

    public ResumeResponseForUser modelToDtoForUser(Resume resume) {
        return mapper.map(resume, ResumeResponseForUser.class);
    }

    public List<ResumeResponseForUser> modelToDtoForUser(List<Resume> users) {
        return users
                .stream()
                .map(this::modelToDtoForUser)
                .collect(Collectors.toList());
    }
}
