package com.fedag.recruitmentSystem.dto.request;

import com.fedag.recruitmentSystem.enums.LevelEducation;
import com.fedag.recruitmentSystem.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

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
    private Role role;

    @NotNull
    private String password;

    @NotNull
    private String calendarId;

    private byte[] image;

    private String imageType;

    private String himselfDescription;
    private LevelEducation levelEducation;
    private String city;
    private byte age;

}
