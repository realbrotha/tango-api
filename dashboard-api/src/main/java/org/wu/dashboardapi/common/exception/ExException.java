package org.wu.dashboardapi.common.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ExException extends RuntimeException{
  private Integer httpStatusCode;
  private String code;
  private String message;

  public ExException(Integer httpStatusCode, String message) {
    super(message);
    this.httpStatusCode = httpStatusCode;
    this.message = message;
  }

  /*
  public ExException(RtCode rtCode, String message) {
    super(message);
    this.httpStatusCode = rtCode.getHttpStatus().value();
    this.code = rtCode.name();
    this.message =message;
  }

   */
}
