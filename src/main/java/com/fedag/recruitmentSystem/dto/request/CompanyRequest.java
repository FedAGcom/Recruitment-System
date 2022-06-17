package com.fedag.recruitmentSystem.dto.request;

import com.fedag.recruitmentSystem.enums.ActiveStatus;
import com.fedag.recruitmentSystem.enums.Role;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRequest {

  @Null
  private Long id;

  @NotNull
  private String companyName;

  @Email
  @NotNull
  private String email;

  @NotNull
  private String location;

  @NotNull
  private Role role;

  @NotNull
  private String password;

  @NotNull
  private String calendarId;

  @NotNull
  private ActiveStatus activeStatus;
}
