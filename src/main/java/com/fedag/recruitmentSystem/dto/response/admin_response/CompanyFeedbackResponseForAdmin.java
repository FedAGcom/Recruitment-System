package com.fedag.recruitmentSystem.dto.response.admin_response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyFeedbackResponseForAdmin {
    private Long id;
    private byte stars;
    private String comment;
    private Long companyId;
    private Long userId;
}
