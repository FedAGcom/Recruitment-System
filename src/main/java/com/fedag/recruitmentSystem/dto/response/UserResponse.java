package com.fedag.recruitmentSystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private LocalDateTime birthday;
    private String calendarId;
    private String activationCode;
    private ActiveStatus activeStatus;
}
