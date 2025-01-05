package com.gwangho.commerce.app.common.exception;


import com.gwangho.commerce.app.common.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StockNotFoundException extends RuntimeException{
    private final ErrorCode errorCode;

    public StockNotFoundException() {
        super(ErrorCode.STOCK_NOT_FOUND.getMessage());
        this.errorCode = ErrorCode.STOCK_NOT_FOUND;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
