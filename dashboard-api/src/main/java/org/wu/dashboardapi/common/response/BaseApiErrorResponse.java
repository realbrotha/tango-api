package org.wu.dashboardapi.common.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseApiErrorResponse implements ApiResponseCode {
    private StatusCode statusCode;
    private Integer status;
    private String message;
    private String detailMessage;

    /*
    @Override
    public StatusCode getStatusCode() { return statusCode; }

    public void setStatusCode(StatusCode statusCode) { this.statusCode = statusCode; }

    public Integer getStatus() { return status; }

    @Override
    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public String getDetailMessage() { return detailMessage; }

    public void setDetailMessage(String detailMessage) { this.detailMessage = detailMessage; }
    */
}
