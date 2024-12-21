package com.gwangho.commerce.app.infra;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisLock {
    // 락 이름
    String key();

    // 락 시간단위
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    // 락 흭득 대기시간
    long waitTime() default 5L;

    long leaseTime() default 3L;
}
