package com.fedag.recruitmentSystem.mapper;

import com.fedag.recruitmentSystem.dto.request.ExperienceRequest;
import com.fedag.recruitmentSystem.dto.request.ExperienceUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.ExperienceResponseForAdmin;
import com.fedag.recruitmentSystem.dto.response.user_response.ExperienceResponseForUser;
import com.fedag.recruitmentSystem.model.Experience;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ExperienceMapper {

    private final ModelMapper mapper;

    public ExperienceResponseForAdmin modelToDto(Experience experience) {
        return mapper.map(experience, ExperienceResponseForAdmin.class);
    }

    public List<ExperienceResponseForAdmin> modelToDto(List<Experience> experience) {
        return experience
                .stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    public Page<ExperienceResponseForAdmin> modelToDto(Page<Experience> all) {
        return all
                .map(new Function<Experience, ExperienceResponseForAdmin>() {
                    @Override
                    public ExperienceResponseForAdmin apply(Experience entity) {
                        return modelToDto(entity);
                    }
                });
    }

    public Experience dtoToModel(ExperienceRequest dto) {
        return mapper.map(dto, Experience.class);
    }

    public Experience dtoToModel(ExperienceUpdateRequest dto) {
        return mapper.map(dto, Experience.class);
    }

    public Experience dtoToModel(ExperienceResponseForAdmin dto) {
        return mapper.map(dto, Experience.class);
    }

    public List<Experience> dtoToModel(List<ExperienceResponseForAdmin> dto) {
        return dto
                .stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }

    public ExperienceResponseForUser modelToDtoForUser(Experience experience) {
        return mapper.map(experience, ExperienceResponseForUser.class);
    }

    public List<ExperienceResponseForUser> modelToDtoForUser(List<Experience> experience) {
        return experience
                .stream()
                .map(this::modelToDtoForUser)
                .collect(Collectors.toList());
    }

    public Page<ExperienceResponseForUser> modelToDtoForUser(Page<Experience> all) {
        return all
                .map(new Function<Experience, ExperienceResponseForUser>() {
                    @Override
                    public ExperienceResponseForUser apply(Experience entity) {
                        return modelToDtoForUser(entity);
                    }
                });
    }
}
