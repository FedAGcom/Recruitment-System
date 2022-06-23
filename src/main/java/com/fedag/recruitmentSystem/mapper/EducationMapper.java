package com.fedag.recruitmentSystem.mapper;

import com.fedag.recruitmentSystem.dto.request.EducationRequest;
import com.fedag.recruitmentSystem.dto.request.EducationUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.UserResponseForAdmin;
import com.fedag.recruitmentSystem.dto.response.admin_response.EducationResponseForAdmin;
import com.fedag.recruitmentSystem.dto.response.user_response.EducationResponseForUser;
import com.fedag.recruitmentSystem.model.Education;
import com.fedag.recruitmentSystem.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EducationMapper {

    private final ModelMapper mapper;

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Education.class, EducationResponseForAdmin.class)
                .addMappings(m -> m.skip(EducationResponseForAdmin::setUser))
                .setPostConverter(toDtoConverter());
    }

    private Converter<Education, EducationResponseForAdmin> toDtoConverter() {
        return context -> {
            Education source = context.getSource();
            EducationResponseForAdmin destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    private void mapSpecificFields(Education source, EducationResponseForAdmin destination) {
       destination.setUser(source.getUser().getId());
    }

    public EducationResponseForAdmin modelToDto(Education education) {
        return mapper.map(education, EducationResponseForAdmin.class);
    }

    public List<EducationResponseForAdmin> modelToDto(List<Education> educations) {
        return educations
                .stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    public Page<EducationResponseForAdmin> modelToDto(Page<Education> all) {
        return all
                .map(new Function<Education, EducationResponseForAdmin>() {
                    @Override
                    public EducationResponseForAdmin apply(Education education) {
                        return modelToDto(education);
                    }
                });
    }

    public Education dtoToModel(EducationRequest dto) {
        return mapper.map(dto, Education.class);
    }

    public Education dtoToModel(EducationUpdateRequest dto) {
        return mapper.map(dto, Education.class);
    }

    public Education dtoToModel(EducationResponseForAdmin dto) {
        return mapper.map(dto, Education.class);
    }

    public List<Education> dtoToModel(List<EducationResponseForAdmin> dto) {
        return dto
                .stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }

    public EducationResponseForUser modelToDtoForUser(Education education) {
        return mapper.map(education, EducationResponseForUser.class);
    }
}
