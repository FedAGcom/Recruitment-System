package com.fedag.recruitmentSystem.dto.request;

import com.fedag.recruitmentSystem.dto.response.ExperienceShortResponseForAdmin;
import com.fedag.recruitmentSystem.enums.ActiveStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeRequest {
    private Long id;

    private String resumeName;

    private ActiveStatus status;

    private LocalDateTime dateCreated;

    private List<ExperienceShortResponseForAdmin> experiences = new ArrayList<>();

    private Long userId;
}
