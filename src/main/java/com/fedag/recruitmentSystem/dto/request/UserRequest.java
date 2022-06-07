package com.fedag.recruitmentSystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private LocalDateTime birthday;
}
