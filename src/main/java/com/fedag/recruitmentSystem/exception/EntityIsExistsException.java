package com.fedag.recruitmentSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntityIsExistsException extends ResponseStatusException {

  public EntityIsExistsException(HttpStatus status, String reason) {
    super(status, reason);
  }
}
