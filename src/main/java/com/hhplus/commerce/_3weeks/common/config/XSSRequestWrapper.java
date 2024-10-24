package com.hhplus.commerce._3weeks.common.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class XSSRequestWrapper extends HttpServletRequestWrapper {
    public XSSRequestWrapper(HttpServletRequest httpRequest) {
        super(httpRequest);
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        return sanitize(value);
    }

    private String sanitize(String value) {
        if (value != null) {
            // 특정 XSS 공격 패턴을 인코딩하거나 제거
            value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
            value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
            value = value.replaceAll("'", "&#39;");
            value = value.replaceAll("\"", "&quot;");
        }
        return value;
    }
}
