package com.gwangho.commerce.app.common;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InsufficientBalanceException extends RuntimeException{
    private final ErrorCode errorCode;

    public InsufficientBalanceException() {
        super(ErrorCode.INSUFFICIENT_BALANCE.getMessage());
        this.errorCode = ErrorCode.INSUFFICIENT_BALANCE;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
