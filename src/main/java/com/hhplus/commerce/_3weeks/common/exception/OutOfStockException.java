package com.hhplus.commerce._3weeks.common.exception;

import com.hhplus.commerce._3weeks.common.error.ErrorCode;
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
