package com.hhplus.commerce._3weeks.domain.user;

import com.hhplus.commerce._3weeks.api.dto.request.OrderRequest;
import com.hhplus.commerce._3weeks.infra.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserReader userReader;
    private final UserUpdater userUpdater;

    public UserEntity getUserInfo(Long id) {
        return userReader.getUserInfo(id);
    }

    public UserEntity chargePoint(Long userId, Long point) {
        UserEntity userInfo = userReader.getUserInfo(userId);

        return userUpdater.charge(userInfo, point);
    }

    public UserEntity payment(UserEntity user, OrderRequest request) {
        return userUpdater.payment(user, request);
    }
}
