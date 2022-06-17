package com.fedag.recruitmentSystem.dto.request;

import com.fedag.recruitmentSystem.dto.response.ExperienceShortResponse;
import com.fedag.recruitmentSystem.dto.response.UserResponse;
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
public class ResumeRequest {

  private Long id;

  private String resumeName;

  private ActiveStatus status;

  private LocalDateTime dateCreated;

  private List<ExperienceShortResponse> experiences = new ArrayList<>();

  private UserResponse user;
}
