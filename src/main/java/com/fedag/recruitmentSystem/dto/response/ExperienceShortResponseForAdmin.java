package com.fedag.recruitmentSystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceShortResponseForAdmin {
    private Long id;

    private String description;

    private LocalDateTime StartDate;

    private LocalDateTime EndDate;
}
