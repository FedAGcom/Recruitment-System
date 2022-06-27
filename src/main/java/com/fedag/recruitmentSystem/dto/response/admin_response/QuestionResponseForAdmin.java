package com.fedag.recruitmentSystem.dto.response.admin_response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponseForAdmin {

    private Long id;
    private String title;
    private String question;
    private String answer;
    private String correct;
}
