package com.gwangho.commerce.app.domain.payment.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PaymentType {
    CASH("CASH", "포인트 결제"),
    CARD("CARD", "카드 결제"),
    TRANSFER("TRANSFER", "계좌 이체"),
    QR_PAY("QR 결제", "모바일 결제");

    private final String method;
    private final String description;

    PaymentType(String method, String description) {
        this.method = method;
        this.description = description;
    }

    public String toString() {
        return method;
    }

}
