package com.hhplus.commerce._3weeks.common.config;

import com.hhplus.commerce._3weeks.infra.order.OrderEntity;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime create_at;

    @LastModifiedDate
    private LocalDateTime update_at;
}