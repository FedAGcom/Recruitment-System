package com.fedag.recruitmentSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author koilng
 * @created 20/06/2022 - 13:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompilerRequest {

  private String clientId;
  private String clientSecret;
  private String script;
  private String language;
  private String versionIndex;
}
