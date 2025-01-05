package com.gwangho.commerce.app.domain.payment.infra;

import com.gwangho.commerce.app.domain.payment.Payment;
import com.gwangho.commerce.app.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<Payment, Long> {

}
