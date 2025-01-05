package com.gwangho.commerce.app.common.exception;

import com.gwangho.commerce.app.common.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException{
    private final ErrorCode errorCode;

    public ProductNotFoundException() {
        super(ErrorCode.PRODUCT_NOT_FOUND.getMessage());
        this.errorCode = ErrorCode.PRODUCT_NOT_FOUND;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
