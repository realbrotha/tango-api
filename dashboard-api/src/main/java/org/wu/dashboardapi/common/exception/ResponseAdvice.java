package org.wu.dashboardapi.common.exception;

import org.wu.dashboardapi.common.response.ApiResponseCode.StatusCode;
import org.wu.dashboardapi.common.response.BaseApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ResponseAdvice {
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public BaseApiErrorResponse handleException (Exception e) {
    log.error("[Exception] message : {}", e.getMessage());
    return setResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", e.getMessage());
  }
  @ExceptionHandler(ExBadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public BaseApiErrorResponse handleBadRequestException (ExBadRequestException e) {
    log.error("[ExBadRequestException] message : {}", e.getMessage());
    return setResponse(HttpStatus.BAD_REQUEST, "Bad Request", e.getMessage());
  }

  @ExceptionHandler(ExUnauthorizedException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public BaseApiErrorResponse handleUnauthorizedException (ExUnauthorizedException e) {
    log.error("[ExUnauthorizedException] message : {}", e.getMessage());
    return setResponse(HttpStatus.UNAUTHORIZED, "Unauthorized", e.getMessage());
  }

  @ExceptionHandler(ExInternalServerErrorException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public BaseApiErrorResponse handleInternalException (ExUnauthorizedException e) {
    log.error("[ExUnauthorizedException] message : {}", e.getMessage());
    return setResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unauthorized", e.getMessage());
  }

  private BaseApiErrorResponse setResponse(HttpStatus httpStatus, String message, String details) {
    BaseApiErrorResponse response = new BaseApiErrorResponse();
    response.setStatusCode(StatusCode.ERROR);
    response.setStatus(httpStatus.value());
    response.setMessage(message);
    response.setDetailMessage(details);

    return response;
  }
}
