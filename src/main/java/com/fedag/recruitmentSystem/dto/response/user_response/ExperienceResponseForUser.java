package com.fedag.recruitmentSystem.dto.response.user_response;

import com.fedag.recruitmentSystem.dto.response.ResumeShortResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceResponseForUser {



    private String description;

    private LocalDateTime StartDate;

    private LocalDateTime EndDate;

    private ResumeShortResponse resume;
}
