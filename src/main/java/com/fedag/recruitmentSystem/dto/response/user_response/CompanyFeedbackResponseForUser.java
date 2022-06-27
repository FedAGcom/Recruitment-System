package com.fedag.recruitmentSystem.dto.response.user_response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyFeedbackResponseForUser {

    private byte stars;
    private String comment;
    private Long companyId;
    private Long userId;
}
