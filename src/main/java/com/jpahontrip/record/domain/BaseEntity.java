package com.jpahontrip.record.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter
public class BaseEntity {

    @Column(name = "created_at")
    private LocalDateTime createdDate;

    @Column(name = "updated_at")
    private LocalDateTime updatedDate;
}
