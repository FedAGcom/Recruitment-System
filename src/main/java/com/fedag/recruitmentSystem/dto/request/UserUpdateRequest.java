package com.fedag.recruitmentSystem.dto.request;

import java.time.LocalDateTime;

import com.fedag.recruitmentSystem.enums.ActiveStatus;
import com.fedag.recruitmentSystem.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {

    @Null
    private Long id;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @Email
    @NotNull
    private String email;

    @NotNull
    private LocalDateTime birthday;
  
    @NotNull
    private String calendarId;

    @NotNull
    private Role role;

    @NotNull
    private String password;
}
