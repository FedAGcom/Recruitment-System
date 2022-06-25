package com.fedag.recruitmentSystem.dto.response.user_response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationResponseForUser {

    private String name;
    private String faculty;
    private String specialty;
    private int age;
    private Long userId;
}
