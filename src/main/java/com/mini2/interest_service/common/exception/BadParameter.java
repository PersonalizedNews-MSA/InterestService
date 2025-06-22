package com.mini2.interest_service.common.exception;

public class BadParameter extends ClientError {
    public BadParameter(String message) {
        super(message);
        this.errorCode = "BadParameter";
        this.errorMessage = message;
    }
}
