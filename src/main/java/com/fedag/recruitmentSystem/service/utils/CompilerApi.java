package com.fedag.recruitmentSystem.service.utils;

import com.fedag.recruitmentSystem.config.CompilerConfig;
import com.fedag.recruitmentSystem.model.CompilerRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author koilng
 * @created 20/06/2022 - 12:42
 */
public class CompilerApi {

  public static ResponseEntity<String> execute(CompilerRequest compilerRequest) {
    return new RestTemplate().postForEntity(CompilerConfig.COMPILER_URL, setCredentials(compilerRequest), String.class);
  }

  private static CompilerRequest setCredentials(CompilerRequest compilerRequest) {
    compilerRequest.setClientId(CompilerConfig.CLIENT_ID);
    compilerRequest.setClientSecret(CompilerConfig.CLIENT_SECRET);
    return compilerRequest;
  }
}
