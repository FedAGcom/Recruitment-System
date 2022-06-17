package com.fedag.recruitmentSystem.mapper;

import com.fedag.recruitmentSystem.dto.request.VacancyRequest;
import com.fedag.recruitmentSystem.dto.request.VacancyUpdateRequest;
import com.fedag.recruitmentSystem.dto.response.VacancyResponse;
import com.fedag.recruitmentSystem.model.Vacancy;
import com.fedag.recruitmentSystem.repository.CompanyRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

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

    mapper.createTypeMap(Vacancy.class, VacancyResponse.class)
        .addMappings(m -> m.skip(VacancyResponse::setCompanyId))
        .setPostConverter(toDtoConverter());
  }

  public VacancyResponse toDto(Vacancy vacancy) {
    return mapper.map(vacancy, VacancyResponse.class);
  }

  public List<VacancyResponse> toDto(List<Vacancy> user) {
    return user
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

  private Converter<Vacancy, VacancyResponse> toDtoConverter() {
    return context -> {
      Vacancy source = context.getSource();
      VacancyResponse destination = context.getDestination();
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

  private void mapSpecificFields(Vacancy source, VacancyResponse destination) {
    destination.setCompanyId(source.getCompany().getId());
  }

  private void mapSpecificFields(VacancyRequest source, Vacancy destination) {
    destination.setCompany(companyRepository.getById(source.getCompanyId()));
  }

  private void mapSpecificFields(VacancyUpdateRequest source, Vacancy destination) {
    destination.setCompany(companyRepository.getById(source.getCompanyId()));
  }
}
