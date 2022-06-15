package com.fedag.recruitmentSystem.dto.request;

import java.time.LocalDateTime;

import com.fedag.recruitmentSystem.enums.ActiveStatus;
import com.fedag.recruitmentSystem.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private LocalDateTime birthday;
    private String calendarId;
    private Role role;
    private String password;
    private ActiveStatus activeStatus;
}
