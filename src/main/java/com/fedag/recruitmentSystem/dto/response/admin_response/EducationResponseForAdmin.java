package com.fedag.recruitmentSystem.dto.response.admin_response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationResponseForAdmin {
    private Long id;
    private String name;
    private String faculty;
    private String specialty;
    private int age;
    private Long user;
}
