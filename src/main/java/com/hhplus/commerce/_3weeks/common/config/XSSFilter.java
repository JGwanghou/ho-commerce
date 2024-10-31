package com.hhplus.commerce._3weeks.common.config;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class XSSFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        XSSRequestWrapper wrappedRequest = new XSSRequestWrapper(httpRequest);

        // 다음 필터 또는 컨트롤러로 요청 전달
        filterChain.doFilter(wrappedRequest, servletResponse);
    }
}
