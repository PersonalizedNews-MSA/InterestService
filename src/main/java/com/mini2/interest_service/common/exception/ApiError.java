package com.mini2.interest_service.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiError extends RuntimeException {
    protected String errorCode;
    protected String errorMessage;

    public ApiError(String message) {
        super(message);
        this.errorMessage = message;
    }
}
