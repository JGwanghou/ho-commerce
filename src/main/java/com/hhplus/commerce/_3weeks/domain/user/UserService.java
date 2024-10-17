package com.hhplus.commerce._3weeks.domain.user;

import com.hhplus.commerce._3weeks.api.dto.request.OrderRequest;
import com.hhplus.commerce._3weeks.infra.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserEntity getUserInfo(Long id) {
        return userRepository.getUserInfo(id);
    }

    public UserEntity payment(UserEntity user, OrderRequest request) {
        user.validPoint(request.getPaymentPrice());

        userRepository.payment(user);

        return user;
    }
}
