package com.hhplus.commerce._3weeks.common.error;

import com.hhplus.commerce._3weeks.common.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCapacityExceededException(ProductNotFoundException e) {
        int code = HttpStatus.NOT_FOUND.value();
        return ResponseEntity.status(code).body(new ErrorResponse(String.valueOf(code), e.getMessage()));
    }


}
