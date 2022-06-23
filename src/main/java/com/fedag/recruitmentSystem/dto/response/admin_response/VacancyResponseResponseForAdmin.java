package com.fedag.recruitmentSystem.dto.response.admin_response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacancyResponseResponseForAdmin {

    private Long id;
    private String message;
    private String status;
    private Long vacancyId;
    private Long userId;
}
