package com.fedag.recruitmentSystem.dto.response;

import com.fedag.recruitmentSystem.enums.ActiveStatus;
import com.fedag.recruitmentSystem.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponse {

  private String companyName;
  private String email;
  private String location;
  private Role role;
  private String activationCode;
  private ActiveStatus activeStatus;

}
