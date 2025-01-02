package com.gwangho.commerce.app.domain.payment.repository;

import com.gwangho.commerce.app.domain.payment.Payment;

public interface PaymentStoreRepository {
    Payment save(Payment entity);
}
