package com.fedag.recruitmentSystem.dto.response.user_response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacancyResponseResponseForUser {

    private String message;
    private String status;
    private Long vacancyId;
    private Long userId;
}
