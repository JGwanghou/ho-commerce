package com.gwangho.commerce.app.common.exception;

import com.gwangho.commerce.app.common.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PriceMismatchException extends RuntimeException{
    private final ErrorCode errorCode;

    public PriceMismatchException() {
        super(ErrorCode.PRICE_MISMATCH.getMessage());
        this.errorCode = ErrorCode.PRICE_MISMATCH;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
