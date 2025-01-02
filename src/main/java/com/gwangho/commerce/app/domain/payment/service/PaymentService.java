package com.gwangho.commerce.app.domain.payment.service;

import com.gwangho.commerce.app.domain.order.Order;
import com.gwangho.commerce.app.domain.payment.Payment;
import com.gwangho.commerce.app.domain.payment.enums.PaymentType;
import com.gwangho.commerce.app.domain.payment.repository.PaymentReaderRepository;
import com.gwangho.commerce.app.domain.payment.repository.PaymentStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentReaderRepository paymentReaderRepository;
    private final PaymentStoreRepository paymentStoreRepository;

    public Payment chargeHistoryInsert(Long userId, PaymentCommand.ChargePoint charge) throws Exception {
        Payment payment = Payment.builder()
                .payMethod(PaymentType.CASH)
                .totalPrice(charge.chargeAmount())
                .build();

        return paymentStoreRepository.save(payment);
    }

    public Payment useHistoryInsert(Long userId, PaymentCommand.UsePoint command) throws Exception {
        Payment payment = Payment.builder()
                .order_id(command.order().getId())
                .payMethod(PaymentType.CASH)
                .totalPrice(command.order().getTotalPrice())
                .build();

        return paymentStoreRepository.save(payment);
    }
}
