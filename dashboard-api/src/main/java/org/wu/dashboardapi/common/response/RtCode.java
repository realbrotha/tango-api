package org.wu.dashboardapi.common.response;

import org.springframework.http.HttpStatus;

public enum RtCode {
  EXECUTED("Executed", HttpStatus.ACCEPTED),
  SUCCESS("Success", HttpStatus.OK),
  SUSPEND("Suspend Execution", HttpStatus.CONTINUE),
  NOT_MODIFIED("Not modified", HttpStatus.NOT_MODIFIED),
  WRONG_PARAMETER("Wrong parameter", HttpStatus.BAD_REQUEST),
  INVALID_PARAMETER("Invalid parameter", HttpStatus.BAD_REQUEST),
  EMPTY_PARAMETER("Empty parameter", HttpStatus.BAD_REQUEST),
  WRONG_STATUS("Wrong status", HttpStatus.BAD_REQUEST),
  MISSING_PATH_VARIABLE("Missing path variable", HttpStatus.BAD_REQUEST),
  PROPAGATION_ERROR("Propagation error", HttpStatus.BAD_REQUEST),
  VALIDATION_FAILURE("Validation error", HttpStatus.BAD_REQUEST),
  BINDING_FAILURE("Binding error", HttpStatus.BAD_REQUEST),
  PARSING_ERROR("Parsing Error", HttpStatus.BAD_REQUEST),
  AUTHENTICATION_FAILURE("Authentication failure", HttpStatus.FORBIDDEN),
  NOT_EXIST("Not exist", HttpStatus.GONE),
  DUPLICATED("Duplicated", HttpStatus.BAD_REQUEST),
  NOT_SUPPORT("Not support", HttpStatus.NOT_FOUND),
  NOT_SUPPORTED_METHOD("Not supported method", HttpStatus.METHOD_NOT_ALLOWED),
  NOT_FOUND("Not found", HttpStatus.NOT_FOUND),
  UNAUTHORIZED("Unauthorized", HttpStatus.UNAUTHORIZED),
  FAILURE("Failure", HttpStatus.INTERNAL_SERVER_ERROR),
  INTERNAL_ERROR("Internal error", HttpStatus.INTERNAL_SERVER_ERROR),
  SERVICE_UNAVAILABLE("Service Unavailable", HttpStatus.SERVICE_UNAVAILABLE),
  RT_AUTHENTICATION_FAILURE("Authentication failure", HttpStatus.FORBIDDEN),
  FORBIDDEN("Permission denied", HttpStatus.FORBIDDEN),
  OPERATOR_SYNTAX_ERROR("OPERATOR_SYNTAX_ERROR", HttpStatus.BAD_REQUEST),
  NOT_FOUND_INSTANCE_MAPPING("NOT_FOUND_INSTANCE_MAPPING", HttpStatus.NOT_FOUND),
  RT_PARSING_ERROR("Parsing Error", HttpStatus.BAD_REQUEST),
  RT_NOT_SUPPORT("Not support", HttpStatus.INTERNAL_SERVER_ERROR),
  RT_INTERNAL_ERROR("Internal error", HttpStatus.INTERNAL_SERVER_ERROR),

  ORGANIZATION_API_INTERNAL_ERROR("Organization Api Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
  ORGANIZATION_API_CONNECTION_ERROR("Can not connect Organization Api Server", HttpStatus.INTERNAL_SERVER_ERROR),
  ORGANIZATION_API_PARSING_ERROR("Organization Api Server Parsing Error", HttpStatus.INTERNAL_SERVER_ERROR),
  ORGANIZATION_API_PROPAGATION_ERROR("Organization Api Server Propagation error", HttpStatus.BAD_REQUEST),

  UNKNOWN("Unknown error. contact to admin", HttpStatus.INTERNAL_SERVER_ERROR);

  private final String rtMessage;
  private final HttpStatus httpStatus;

  RtCode(String rtMessage, HttpStatus httpStatus) {
    this.rtMessage = rtMessage;
    this.httpStatus = httpStatus;
  }

  public String getRtMessage() {
    return this.rtMessage;
  }

  public HttpStatus getHttpStatus() {
    return this.httpStatus;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[").append(this.rtMessage).append("] ");
    return sb.toString();
  }
}