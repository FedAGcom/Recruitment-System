package com.fedag.recruitmentSystem.dto.request;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
