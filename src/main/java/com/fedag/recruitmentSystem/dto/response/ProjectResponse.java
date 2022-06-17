package com.fedag.recruitmentSystem.dto.response;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponse {

  private Long id;
  private String projectName;
  private String description;
  private String requirement;
  private Date dateStart;
  private Date dateEnd;
  private Long companyId;
}
