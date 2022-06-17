package com.fedag.recruitmentSystem.security.security_exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

@Getter
public class ActivationException extends AuthenticationException {

  private HttpStatus httpStatus;

  public ActivationException(String msg, HttpStatus httpStatus) {
    super(msg);
    this.httpStatus = httpStatus;
  }

  public ActivationException(String msg) {
    super(msg);
  }
}
