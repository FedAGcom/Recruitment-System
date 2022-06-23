package com.fedag.recruitmentSystem.mapper;

import com.fedag.recruitmentSystem.dto.request.VacancyRequest;
import com.fedag.recruitmentSystem.dto.request.VacancyUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.admin_response.VacancyResponseForAdmin;
import com.fedag.recruitmentSystem.dto.response.user_response.VacancyResponseForUser;
import com.fedag.recruitmentSystem.model.Vacancy;
import com.fedag.recruitmentSystem.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VacancyMapper {

    private final ModelMapper mapper;
    private final CompanyRepository companyRepository;

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(VacancyRequest.class, Vacancy.class)
                .addMappings(m -> m.skip(Vacancy::setCompany))
                .setPostConverter(toEntityConverterFromRequest());

        mapper.createTypeMap(VacancyUpdateRequest.class, Vacancy.class)
                .addMappings(m -> m.skip(Vacancy::setCompany))
                .setPostConverter(toEntityConverterFromUpdateRequest());

        mapper.createTypeMap(Vacancy.class, VacancyResponseForAdmin.class)
                .addMappings(m -> m.skip(VacancyResponseForAdmin::setCompanyId))
                .setPostConverter(toDtoConverter());

        mapper.createTypeMap(Vacancy.class, VacancyResponseForUser.class)
                .addMappings(m -> m.skip(VacancyResponseForUser::setCompanyId))
                .setPostConverter(toDtoConverterForUser());
    }

    public VacancyResponseForAdmin toDto(Vacancy vacancy) {
        return mapper.map(vacancy, VacancyResponseForAdmin.class);
    }

    public List<VacancyResponseForAdmin> toDto(List<Vacancy> vacancies) {
        return vacancies
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Vacancy toEntity(VacancyRequest vacancyRequest) {
        return mapper.map(vacancyRequest, Vacancy.class);
    }

    public Vacancy toEntity(VacancyUpdateRequest vacancyUpdateRequest) {
        return mapper.map(vacancyUpdateRequest, Vacancy.class);
    }

    private Converter<Vacancy, VacancyResponseForAdmin> toDtoConverter() {
        return context -> {
            Vacancy source = context.getSource();
            VacancyResponseForAdmin destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    private Converter<VacancyRequest, Vacancy> toEntityConverterFromRequest() {
        return context -> {
            VacancyRequest source = context.getSource();
            Vacancy destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    private Converter<VacancyUpdateRequest, Vacancy> toEntityConverterFromUpdateRequest() {
        return context -> {
            VacancyUpdateRequest source = context.getSource();
            Vacancy destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    private Converter<Vacancy, VacancyResponseForUser> toDtoConverterForUser() {
        return context -> {
            Vacancy source = context.getSource();
            VacancyResponseForUser destination = context.getDestination();
            mapSpecificFieldsForUser(source, destination);
            return context.getDestination();
        };
    }

    private void mapSpecificFields(Vacancy source, VacancyResponseForAdmin destination) {
        destination.setCompanyId(source.getCompany().getId());
    }

    private void mapSpecificFields(VacancyRequest source, Vacancy destination) {
        destination.setCompany(companyRepository.getById(source.getCompanyId()));
    }

    private void mapSpecificFields(VacancyUpdateRequest source, Vacancy destination) {
        destination.setCompany(companyRepository.getById(source.getCompanyId()));
    }

    private void mapSpecificFieldsForUser(Vacancy source, VacancyResponseForUser destination) {
        destination.setCompanyId(source.getCompany().getId());
    }

    public VacancyResponseForUser toDtoForUser(Vacancy vacancy) {
        return mapper.map(vacancy, VacancyResponseForUser.class);
    }

    public List<VacancyResponseForUser> toDtoForUser(List<Vacancy> vacancies) {
        return vacancies
                .stream()
                .map(this::toDtoForUser)
                .collect(Collectors.toList());
    }
}
