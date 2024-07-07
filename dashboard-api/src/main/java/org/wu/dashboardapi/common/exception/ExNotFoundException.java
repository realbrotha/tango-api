package org.wu.dashboardapi.common.exception;

import org.springframework.http.HttpStatus;

public class ExNotFoundException extends ExException {
  public ExNotFoundException(String message) {
    super(HttpStatus.NOT_FOUND.value(), message);
  }
}
