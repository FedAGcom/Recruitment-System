package com.fedag.recruitmentSystem.dto.response.user_response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponseForUser {


    private String projectName;
    private String description;
    private String requirement;
    private Date dateStart;
    private Date dateEnd;
    private Long companyId;
}
