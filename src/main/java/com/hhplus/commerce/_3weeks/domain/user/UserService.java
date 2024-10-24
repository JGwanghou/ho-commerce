package com.hhplus.commerce._3weeks.domain.user;

import com.hhplus.commerce._3weeks.api.dto.request.OrderRequest;
import com.hhplus.commerce._3weeks.domain.product.Product;
import com.hhplus.commerce._3weeks.infra.user.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserReader userReader;
    private final UserUpdater userUpdater;

    private final ConcurrentHashMap<Long, UserEntity> userCache = new ConcurrentHashMap<>();

    public UserEntity getUserInfo(Long id) {
        return userReader.getUserInfo(id);
    }

    @Transactional
    public UserEntity chargePoint(Long userId, Long point) {
        UserEntity userInfo = userCache.computeIfAbsent(userId, userReader::getUserInfo);

        UserEntity addPointUser = userUpdater.charge(userInfo, point);

        userCache.put(userId, addPointUser);

        return addPointUser;
    }

    @Transactional
    public UserEntity payment(Long userId, List<Product> products, Long point) {
        UserEntity userInfo = userCache.computeIfAbsent(userId, userReader::getUserInfo);

        UserEntity decreaseUser = userUpdater.payment(userInfo, products, point);

        userCache.put(userId, decreaseUser);

        return decreaseUser;
    }
}
