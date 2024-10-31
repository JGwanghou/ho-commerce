package com.hhplus.commerce._3weeks.common.error;


import lombok.Getter;

@Getter
public enum ErrorCode {
    INSUFFICIENT_BALANCE("1000", "잔액이 부족합니다"),
    PRICE_MISMATCH("1001", "결제 요청 가격과 상품 총합 가격이 일치하지 않습니다"),

    PRODUCT_NOT_FOUND("2000", "상품을 찾을 수 없습니다"),
    OUT_OF_STOCK("2001", "재고가 부족합니다"),

    USER_NOT_FOUND("3001", "존재하지 않는 유저입니다.");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
