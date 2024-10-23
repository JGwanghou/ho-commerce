package com.hhplus.commerce._3weeks.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PriceMismatchException extends RuntimeException{
    public PriceMismatchException(String message) {
        super(message);
    }
}
