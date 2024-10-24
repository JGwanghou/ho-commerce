package com.hhplus.commerce._3weeks.common.error;

import com.hhplus.commerce._3weeks.common.exception.InsufficientBalanceException;
import com.hhplus.commerce._3weeks.common.exception.OutOfStockException;
import com.hhplus.commerce._3weeks.common.exception.PriceMismatchException;
import com.hhplus.commerce._3weeks.common.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException e) {
        int code = HttpStatus.NOT_FOUND.value();
        return ResponseEntity.status(code).body(new ErrorResponse(String.valueOf(code), e.getMessage()));
    }

    @ExceptionHandler(value = InsufficientBalanceException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientBalanceExceededException(InsufficientBalanceException e) {
        int code = HttpStatus.CONFLICT.value();
        return ResponseEntity.status(code).body(new ErrorResponse(String.valueOf(code), e.getMessage()));
    }

    @ExceptionHandler(value = OutOfStockException.class)
    public ResponseEntity<ErrorResponse> handleOutOfStockException(OutOfStockException e) {
        int code = HttpStatus.CONFLICT.value();
        return ResponseEntity.status(code).body(new ErrorResponse(String.valueOf(code), e.getMessage()));
    }

    @ExceptionHandler(value = PriceMismatchException.class)
    public ResponseEntity<ErrorResponse> handlePriceMismatchException(PriceMismatchException e) {
        int code = HttpStatus.BAD_REQUEST.value();
        return ResponseEntity.status(code).body(new ErrorResponse(String.valueOf(code), e.getMessage()));
    }
}
