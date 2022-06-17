package com.fedag.recruitmentSystem.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {

  private Long id;
  private String entityType;
  private LocalDateTime dateCreated;
  private String status;
  private String message;
  private Long userId;
  private Long companyId;
}
