package com.gwangho.commerce.app.common.exception;


import com.gwangho.commerce.app.common.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidChargeAmountException extends RuntimeException{
    private final ErrorCode errorCode;

    public InvalidChargeAmountException() {
        super(ErrorCode.INVALID_AMOUNT.getMessage());
        this.errorCode = ErrorCode.INVALID_AMOUNT;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
