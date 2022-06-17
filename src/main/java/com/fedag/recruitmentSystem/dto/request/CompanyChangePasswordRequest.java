package com.fedag.recruitmentSystem.dto.request;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyChangePasswordRequest {

  @NotNull
  private Long id;

  @NotNull
  private String password;
}
