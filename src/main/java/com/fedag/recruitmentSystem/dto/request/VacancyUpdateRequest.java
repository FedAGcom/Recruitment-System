package com.fedag.recruitmentSystem.dto.request;

import com.fedag.recruitmentSystem.enums.ActiveStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacancyUpdateRequest {

  private Long id;
  private String header;
  private String description;
  private int salary;
  private String experience;
  private ActiveStatus status;
  private LocalDateTime dateCreated;
  private Long companyId;
}
