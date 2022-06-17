package com.fedag.recruitmentSystem.dto.response;

import com.fedag.recruitmentSystem.enums.ActiveStatus;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeResponse {

  private Long id;

  private String resumeName;

  private ActiveStatus status;

  private LocalDateTime dateCreated;

  private List<ExperienceShortResponse> experiences = new ArrayList<>();

  private UserResponse user;
}
