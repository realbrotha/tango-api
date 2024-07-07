package org.wu.dashboardapi.common.exception;

import org.springframework.http.HttpStatus;

public class ExBadRequestException extends ExException {
  public ExBadRequestException(String message) { super(HttpStatus.BAD_REQUEST.value(), message); }

}
