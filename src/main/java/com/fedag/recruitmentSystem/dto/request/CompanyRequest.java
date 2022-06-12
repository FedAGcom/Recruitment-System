package com.fedag.recruitmentSystem.dto.request;

import com.fedag.recruitmentSystem.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRequest {

    private Long id;
    private String companyName;
    private String email;
    private String location;
    private Role role;
    private String password;
}
