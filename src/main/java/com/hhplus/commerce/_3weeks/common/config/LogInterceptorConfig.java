package com.hhplus.commerce._3weeks.common.config;

import com.hhplus.commerce._3weeks.api.intercepter.LogPrintInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class LogInterceptorConfig implements WebMvcConfigurer {

    private final LogPrintInterceptor logPrintInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logPrintInterceptor)
                .addPathPatterns("/api/v1/users/*")
                .addPathPatterns("/api/v1/carts/*")
                .addPathPatterns("/api/v1/orders/*")
                .addPathPatterns("/api/v1/products/*");
    }
}
