package com.fedag.recruitmentSystem.dto.response;

import com.fedag.recruitmentSystem.enums.ActiveStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeShortResponse {

  private Long id;

  private String resumeName;

  private ActiveStatus status;

  private LocalDateTime dateCreated;
}
