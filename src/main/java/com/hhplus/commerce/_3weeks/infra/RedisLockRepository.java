package com.hhplus.commerce._3weeks.infra;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@RequiredArgsConstructor
@Component
public class RedisLockRepository {
    private final RedisTemplate<String, String> redisTemplate;

    public Boolean lock(Object key) {
        return redisTemplate
                .opsForValue()
                .setIfAbsent(key.toString(), "lock", Duration.ofMillis(3000));
    }

    public Boolean unlock(Object key) {
        return redisTemplate.delete(key.toString());
    }
}
