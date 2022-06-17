package com.fedag.recruitmentSystem.exception;

/**
 * @author koilng
 * @created 15/06/2022 - 17:24
 */
public class EventAttendeeAlreadyExists extends RuntimeException {

  public EventAttendeeAlreadyExists() {
  }

  public EventAttendeeAlreadyExists(String message) {
    super(message);
  }

  public EventAttendeeAlreadyExists(String message, Throwable cause) {
    super(message, cause);
  }

  public EventAttendeeAlreadyExists(Throwable cause) {
    super(cause);
  }

  public EventAttendeeAlreadyExists(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
