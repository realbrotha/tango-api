package org.wu.dashboardapi.common.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseDto<T> {
    public static final ApiResponseDto<String> DEFAULT_UNAUTHORIZED = new ApiResponseDto<>(RtCode.SUCCESS);
    public static final ApiResponseDto<String> DEFAULT_OK = new ApiResponseDto<>(RtCode.SUCCESS);

    private RtCode code;
    private String message;
    private T data;

    public ApiResponseDto(RtCode status) {
        this.bindStatus(status);
    }
    public ApiResponseDto(RtCode status, T data) {
        this.bindStatus(status);
        this.data = data;
    }
    public ApiResponseDto(RtCode status, String message) {
        this.bindStatus(status);
        this.message = message;
    }

    private void bindStatus (RtCode status) {
        this.code = status;
        this.message = status.getRtMessage();
    }

//    @Override
//    public StatusCode getStatusCode() { return statusCode; }
//
//    public void setStatusCode(StatusCode statusCode) { this.statusCode = statusCode; }
//
//    public String getMessage() { return message; }
//    public void setMessage(String message) { this.message = message; }
//    public T getData() { return data; }
//    public void setData(T data) {
//        this.data= data;
//    }
//    public Integer getStatus() { return status; }
//    public void setStatus(Integer status) { this.status = status; }

}
