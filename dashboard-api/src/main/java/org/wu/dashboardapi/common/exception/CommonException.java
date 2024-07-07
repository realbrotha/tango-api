package org.wu.dashboardapi.common.exception;

import org.wu.dashboardapi.common.exception.messages.ErrorMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonException extends Exception {
    private ErrorMessage errorMessage;
    public CommonException(ErrorMessage errorMessage) { this.errorMessage = errorMessage; }
    public CommonException(Throwable cause) { this(-1, cause); }
    public CommonException(int messageCode, Object... args) { this(messageCode, null, args); }
    public CommonException(int messageCode) { this(messageCode, null, (Object) null); }

    public CommonException(int messageCode, Throwable cause) { this(messageCode, cause, (Object)null); }

    public CommonException(int messageCode, Throwable cause, Object... args) {
        super(cause);
        this.errorMessage = new ErrorMessage(messageCode, String.valueOf(args[0]), args);
        this.errorMessage.setThrowable(cause);
    }
    public int getErrorCode() { return errorMessage.getErrorCode(); }
}
