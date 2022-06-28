package com.fedag.recruitmentSystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyChangePasswordRequest {
    @NotNull
    private Long id;

    @NotNull
    private String password;
}
