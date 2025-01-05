package com.gwangho.commerce.app.api.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingResponseWrapper;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Component
@RequiredArgsConstructor
@Slf4j
public class IdempotencyInterceptor implements HandlerInterceptor {
    private final RedisTemplate<String, Object> redisTemplate;
    private static final String IDEMPOTENCY_HEADER = "Idempotency-Key";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(HttpMethod.POST.matches(request.getMethod())){
            Thread.sleep(500);
            String idempotencyKey = request.getHeader(IDEMPOTENCY_HEADER);
            if (idempotencyKey == null || idempotencyKey.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "멱등성 키가 누락되었습니다.");
                return false;
            }

            Boolean exists = redisTemplate.hasKey(idempotencyKey);
            if(Boolean.TRUE.equals(exists)){
                String redisValue = (String) redisTemplate.opsForValue().get(idempotencyKey);
                if (redisValue.equals("process")) {
                    response.sendError(HttpServletResponse.SC_CONFLICT, "포인트 충전을 처리중입니다.");
                    return false;
                }

                // 레디스에 저장된 캐싱데이터
                String decodedResponse = new String(redisValue.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                response.setContentType("application/json");
                response.getWriter().write(decodedResponse);
                return false;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(HttpMethod.POST.matches(request.getMethod())) {
            String idempotencyKey = request.getHeader(IDEMPOTENCY_HEADER);
            redisTemplate.opsForValue().set(idempotencyKey, "process");

            // PointResponse 직렬화
            final ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;
            byte[] contentAsByteArray = cachingResponse.getContentAsByteArray();

            // 레디스 저장
            String resultCacheValue = new String(contentAsByteArray, StandardCharsets.UTF_8);
            redisTemplate.opsForValue().set(
                    idempotencyKey,
                    resultCacheValue,
                    Duration.ofHours(1)
            );
        }
    }


}
