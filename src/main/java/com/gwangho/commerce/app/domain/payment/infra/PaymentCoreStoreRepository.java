package com.gwangho.commerce.app.domain.payment.infra;

import com.gwangho.commerce.app.domain.payment.Payment;
import com.gwangho.commerce.app.domain.payment.repository.PaymentStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentCoreStoreRepository implements PaymentStoreRepository {

    private final PaymentJpaRepository paymentJpaRepository;

    @Override
    public Payment save(Payment entity) {
        return paymentJpaRepository.save(entity);
    }
}
