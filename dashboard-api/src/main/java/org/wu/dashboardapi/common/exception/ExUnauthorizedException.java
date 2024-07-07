package org.wu.dashboardapi.common.exception;

import org.springframework.http.HttpStatus;

public class ExUnauthorizedException extends ExException {
  public ExUnauthorizedException(String message) {
    super(HttpStatus.UNAUTHORIZED.value(), message);
  }
}
