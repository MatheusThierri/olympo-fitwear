package com.olympofitwear.olympo.olympo_api.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Payment {
    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    private OffsetDateTime paymentDate;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Order order;
}
