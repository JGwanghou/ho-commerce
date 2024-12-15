package com.gwangho.commerce.app.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    public LocalDateTime create_at;

    @LastModifiedDate
    public LocalDateTime update_at;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    public boolean is_delete;
}
