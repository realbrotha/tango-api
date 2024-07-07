package org.wu.dashboardapi.common.response;

import java.io.Serializable;

public interface ApiResponseCode extends Serializable {
    enum StatusCode {
        OK,
        ERROR
    }
    StatusCode getStatusCode();
    String getMessage();
}
