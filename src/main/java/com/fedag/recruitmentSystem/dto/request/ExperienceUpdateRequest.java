package com.fedag.recruitmentSystem.dto.request;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceUpdateRequest {
    private Long id;

    private String description;

    private LocalDateTime StartDate;

    private LocalDateTime EndDate;
}
