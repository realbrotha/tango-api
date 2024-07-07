package org.wu.dashboardapi.common.exception.messages;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {
    public enum MessageType {
        WARNING,
        ERROR
    }

    private MessageType type;
    private Integer errorCode;
    private String message;
    private Object[] args;
    private Throwable throwable;
    public ErrorMessage () {

    }

    public ErrorMessage(MessageType type, int errorCode) { this(type, errorCode, null); }
    public ErrorMessage(MessageType type, String message) { this(type, 0, message); }

    public ErrorMessage(int errorCode, Object... args) {
        this(MessageType.ERROR, errorCode, null, args);
    }

    public ErrorMessage(int errorCode, String message, Object... args) {
        this(MessageType.ERROR, errorCode, message, args);
    }

    public ErrorMessage(MessageType type, int errorCode, String message, Object... args) {
        this.type = type;
        this.errorCode = errorCode;
        this.message = message;
        this.args = args;
    }
}
