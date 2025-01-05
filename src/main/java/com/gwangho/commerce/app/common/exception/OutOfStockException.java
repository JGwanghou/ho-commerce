package com.gwangho.commerce.app.common.exception;

import com.gwangho.commerce.app.common.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class OutOfStockException extends RuntimeException {
    private final ErrorCode errorCode;

    public OutOfStockException() {
        super(ErrorCode.OUT_OF_STOCK.getMessage());
        this.errorCode = ErrorCode.OUT_OF_STOCK;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
