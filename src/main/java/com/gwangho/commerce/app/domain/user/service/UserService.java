package com.gwangho.commerce.app.domain.user.service;

import com.gwangho.commerce.app.common.exception.InsufficientBalanceException;
import com.gwangho.commerce.app.common.exception.InvalidChargeAmountException;
import com.gwangho.commerce.app.domain.payment.service.PaymentCommand;
import com.gwangho.commerce.app.domain.user.User;
import com.gwangho.commerce.app.domain.user.event.PaymentEventPublisher;
import com.gwangho.commerce.app.domain.user.event.PaymentFailedEvent;
import com.gwangho.commerce.app.domain.user.event.PaymentSucceedEvent;
import com.gwangho.commerce.app.domain.user.repository.UserReaderRepository;
import com.gwangho.commerce.app.domain.user.repository.UserStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserReaderRepository userReaderRepository;
    private final UserStoreRepository userStoreRepository;
    private final PaymentEventPublisher paymentEventPublisher;

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
            target.decreasePoint(command.order().getTotalPrice());
            paymentEventPublisher.success(new PaymentSucceedEvent(command.order().getUserId(), command.order()));
        }catch (InsufficientBalanceException | InvalidChargeAmountException e){
            paymentEventPublisher.fail(new PaymentFailedEvent(command.order().getUserId(), command.order()));
        }

        return target;
    }
}
