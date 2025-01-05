package com.gwangho.commerce.app.common;


import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_AMOUNT("4001", "잘못된 요청 금액입니다."),
    PRICE_MISMATCH("4002", "결제 요청 가격과 상품 총합 가격이 일치하지 않습니다"),
    INSUFFICIENT_BALANCE("4003", "잔액이 부족합니다"),
    OUT_OF_STOCK("4004", "재고가 부족합니다"),

    USER_NOT_FOUND("4005", "존재하지 않는 유저입니다."),
    PRODUCT_NOT_FOUND("4006", "상품을 찾을 수 없습니다"),
    STOCK_NOT_FOUND("4007", "해당 상품의 재고정보를 찾을 수 없습니다.");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
