package com.fedag.recruitmentSystem.domain.mapper;

import com.fedag.recruitmentSystem.domain.dto.VacancyRequest;
import com.fedag.recruitmentSystem.domain.dto.VacancyResponse;
import com.fedag.recruitmentSystem.domain.entity.Vacancy;
import com.fedag.recruitmentSystem.repository.CompanyRepository;
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
        .addMappings(m -> m.skip(Vacancy::setId))
        .setPostConverter(toEntityConverter());

    mapper.createTypeMap(Vacancy.class, VacancyResponse.class)
        .addMappings(m -> m.skip(VacancyResponse::setCompanyId))
        .setPostConverter(toDtoConverter());
  }

  public VacancyResponse toDto(Vacancy vacancy) {
    return mapper.map(vacancy, VacancyResponse.class);
  }

  public Vacancy toEntity(VacancyRequest vacancyRequest) {
    return mapper.map(vacancyRequest, Vacancy.class);
  }

  private Converter<Vacancy, VacancyResponse> toDtoConverter() {
    return context -> {
      Vacancy source = context.getSource();
      VacancyResponse destination = context.getDestination();
      mapSpecificFields(source, destination);
      return context.getDestination();
    };
  }

  private Converter<VacancyRequest, Vacancy> toEntityConverter() {
    return context -> {
      VacancyRequest source = context.getSource();
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
}
