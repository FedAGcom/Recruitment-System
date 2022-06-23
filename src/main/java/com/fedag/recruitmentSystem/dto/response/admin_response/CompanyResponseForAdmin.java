package com.fedag.recruitmentSystem.dto.response.admin_response;

import com.fedag.recruitmentSystem.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponseForAdmin {

  private Long id;
  private String companyName;
  private String email;
  private String location;
  private Role role;
  private String calendarId;
  private String activationCode;
  private String password;
}
