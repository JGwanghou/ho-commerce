package com.hhplus.commerce._3weeks.common.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<XSSFilter> xssFilter() {
        FilterRegistrationBean<XSSFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new XSSFilter());
        registrationBean.addUrlPatterns("/*");  // 모든 경로에 대해 필터 적용
        registrationBean.setOrder(1); // 필터 적용 순서
        return registrationBean;
    }
}
