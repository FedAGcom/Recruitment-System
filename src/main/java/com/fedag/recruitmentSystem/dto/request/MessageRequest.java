package com.fedag.recruitmentSystem.dto.request;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest {

  private Long id;
  private String entityType;
  private LocalDateTime dateCreated;
  private String status;
  private String message;
  private Long userId;
  private Long companyId;

}
