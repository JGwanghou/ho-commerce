package com.gwangho.commerce.app.domain.user.service;

import com.gwangho.commerce.app.domain.point.service.PointCommand;
import com.gwangho.commerce.app.domain.user.User;
import com.gwangho.commerce.app.domain.user.repository.UserReaderRepository;
import com.gwangho.commerce.app.domain.user.repository.UserStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserReaderRepository userReaderRepository;
    private final UserStoreRepository userStoreRepository;

    public User findByIdOruUserNotFoundThrow(Long userId) {
        return userReaderRepository.findByIdOruUserNotFoundThrow(userId);
    }

    @Transactional
    public User addPoint(Long userId, PointCommand.ChargePoint charge) {
        User target = userReaderRepository.findByIdOruUserNotFoundThrow(userId);
        target.addPoint(charge.chargeAmount());
        return target;
    }
}
