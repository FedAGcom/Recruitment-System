package com.fedag.recruitmentSystem.controller;

import com.fedag.recruitmentSystem.model.CompilerRequest;
import com.fedag.recruitmentSystem.service.utils.CompilerApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author koilng
 * @created 20/06/2022 - 11:48
 */
@RequestMapping("/api/compiler")
@Tag(name = "Контроллер компилятора", description = "Работа с компилятором")
@RestController
public class CompilerController {

  @Operation(summary = "Скомпилировать код", security = @SecurityRequirement(name = "bearerAuth"))
  @PreAuthorize("hasAuthority('READ')")
  @PostMapping("/compile")
  public ResponseEntity<String> doCompile(@RequestBody CompilerRequest compilerRequest) {
    return CompilerApi.execute(compilerRequest);
  }
}


