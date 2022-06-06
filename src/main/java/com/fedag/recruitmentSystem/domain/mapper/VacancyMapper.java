package com.fedag.recruitmentSystem.domain.mapper;

import com.fedag.recruitmentSystem.domain.dto.VacancyDto;
import com.fedag.recruitmentSystem.domain.entity.Vacancy;
import com.fedag.recruitmentSystem.repository.CompanyRepository;
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
    mapper.createTypeMap(VacancyDto.class, Vacancy.class)
        .addMappings(m -> m.skip(Vacancy::setCompany))
        .addMappings(m -> m.skip(Vacancy::setId))
        .setPostConverter(toEntityConverter());

    mapper.createTypeMap(Vacancy.class, VacancyDto.class)
        .addMappings(m -> m.skip(VacancyDto::setCompanyId))
        .setPostConverter(toDtoConverter());
  }

  public VacancyDto toDto(Vacancy vacancy) {
    return mapper.map(vacancy, VacancyDto.class);
  }

  public Vacancy toEntity(VacancyDto vacancyDto) {
    return mapper.map(vacancyDto, Vacancy.class);
  }

  private Converter<Vacancy, VacancyDto> toDtoConverter() {
    return context -> {
      Vacancy source = context.getSource();
      VacancyDto destination = context.getDestination();
      mapSpecificFields(source, destination);
      return context.getDestination();
    };
  }

  private Converter<VacancyDto, Vacancy> toEntityConverter() {
    return context -> {
      VacancyDto source = context.getSource();
      Vacancy destination = context.getDestination();
      mapSpecificFields(source, destination);
      return context.getDestination();
    };
  }

  private void mapSpecificFields(Vacancy source, VacancyDto destination) {
    destination.setCompanyId(source.getCompany().getId());
  }

  private void mapSpecificFields(VacancyDto source, Vacancy destination) {
    destination.setCompany(companyRepository.getById(source.getCompanyId()));
  }
}
