package com.fedag.recruitmentSystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequest {

    private Long id;
    private String projectName;
    private String description;
    private String requirement;
    private Date dateStart;
    private Date dateEnd;
    private Long companyId;
}
