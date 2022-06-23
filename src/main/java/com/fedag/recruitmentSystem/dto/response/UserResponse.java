package com.fedag.recruitmentSystem.dto.response;

import com.fedag.recruitmentSystem.enums.ActiveStatus;
import com.fedag.recruitmentSystem.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import com.fedag.recruitmentSystem.enums.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private LocalDateTime birthday;
    private Role role;
    private String calendarId;
    private String activationCode;
    private ActiveStatus activeStatus;
    private String image;
    private String imageType;
    private String himselfDescription;
    private LevelEducation levelEducation;
    private String city;
    private byte age;
}
