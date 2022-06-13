package com.fedag.recruitmentSystem.dto.response;

import com.fedag.recruitmentSystem.enums.ActiveStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacancyResponse {

  private String header;
  private String description;
  private int salary;
  private String experience;
  private ActiveStatus status;
  private LocalDateTime dateCreated;
  private Long companyId;

}
