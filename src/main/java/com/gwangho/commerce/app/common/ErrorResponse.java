package com.gwangho.commerce.app.common;

public record ErrorResponse(
        String code,
        String message
) {
}
