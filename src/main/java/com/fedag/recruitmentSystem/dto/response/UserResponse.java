package com.fedag.recruitmentSystem.dto.response;

import com.fedag.recruitmentSystem.enums.ActiveStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

  private Long id;
  private String firstname;
  private String lastname;
  private String email;
  private LocalDateTime birthday;
  private String calendarId;
  private String activationCode;
  private ActiveStatus activeStatus;
}
