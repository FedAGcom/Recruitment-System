package com.fedag.recruitmentSystem.security;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthenticationRequestOTP {
    private String email;
    private String code;
}
