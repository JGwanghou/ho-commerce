package com.gwangho.commerce.app.domain.user.service;

import com.gwangho.commerce.app.common.exception.InsufficientBalanceException;
import com.gwangho.commerce.app.common.exception.InvalidChargeAmountException;
import com.gwangho.commerce.app.domain.payment.service.PaymentCommand;
import com.gwangho.commerce.app.domain.user.User;
import com.gwangho.commerce.app.domain.user.event.UsePointEventPublisher;
import com.gwangho.commerce.app.domain.user.event.UsePointFailedEvent;
import com.gwangho.commerce.app.domain.user.event.UsePointSucceedEvent;
import com.gwangho.commerce.app.domain.user.repository.UserReaderRepository;
import com.gwangho.commerce.app.domain.user.repository.UserStoreRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserReaderRepository userReaderRepository;
    private final UserStoreRepository userStoreRepository;
    private final UsePointEventPublisher usePointEventPublisher;

    public Optional<User> findById(Long userId) {
        return userReaderRepository.findById(userId);
    }

    public User findByIdOruUserNotFoundThrow(Long userId) {
        return userReaderRepository.findByIdOruUserNotFoundThrow(userId);
    }

    @Transactional
    public User addPoint(Long userId, PaymentCommand.ChargePoint command) {
        User target = userReaderRepository.findByIdOruUserNotFoundThrow(userId);
        target.addPoint(command.chargeAmount());
        return target;
    }

    @Transactional
    public User usePoint(PaymentCommand.UsePoint command) {
        User target = userReaderRepository.findByIdOruUserNotFoundThrow(command.order().getUserId());
        try{
            log.info("차감 될 포인트 {} ", command.order().getTotalPrice());
            target.decreasePoint(command.order().getTotalPrice());
            usePointEventPublisher.success(new UsePointSucceedEvent(command.order(), command.orderItemList()));
        }catch (InsufficientBalanceException | InvalidChargeAmountException e){
            usePointEventPublisher.fail(new UsePointFailedEvent(command.order(), command.orderItemList()));
        }

        return target;
    }
}
