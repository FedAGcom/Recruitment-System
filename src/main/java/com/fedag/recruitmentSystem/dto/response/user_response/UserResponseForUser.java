package com.fedag.recruitmentSystem.dto.response.user_response;

import com.fedag.recruitmentSystem.enums.LevelEducation;
import com.fedag.recruitmentSystem.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseForUser {


    private String firstname;
    private String lastname;
    private String email;
    private LocalDateTime birthday;
    private String image;
    private String imageType;
    private String himselfDescription;
    private LevelEducation levelEducation;
    private String city;
    private byte age;
}
