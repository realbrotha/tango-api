package org.wu.dashboardapi.common.exception;

import org.springframework.http.HttpStatus;

public class ExInternalServerErrorException extends ExException {

  public ExInternalServerErrorException(String message) {
    super(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
  }
}
