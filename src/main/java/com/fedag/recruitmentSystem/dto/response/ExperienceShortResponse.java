package com.fedag.recruitmentSystem.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceShortResponse {

  private Long id;

  private String description;

  private LocalDateTime StartDate;

  private LocalDateTime EndDate;
}
