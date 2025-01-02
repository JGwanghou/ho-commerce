package com.gwangho.commerce.app.domain.payment.infra;

import com.gwangho.commerce.app.domain.payment.repository.PaymentReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentCoreReaderRepository implements PaymentReaderRepository {
    private final PaymentJpaRepository paymentJpaRepository;


}
