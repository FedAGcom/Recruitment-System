package com.fedag.recruitmentSystem.service.utils;

import com.fedag.recruitmentSystem.model.CompilerRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author koilng
 * @created 20/06/2022 - 12:42
 */
public class CompilerApi {

  private static final String COMPILER_URL = "https://api.jdoodle.com/v1/execute";

  public static ResponseEntity<String> execute(CompilerRequest compilerRequest) {
    return new RestTemplate().postForEntity(COMPILER_URL, compilerRequest, String.class);
  }
}
