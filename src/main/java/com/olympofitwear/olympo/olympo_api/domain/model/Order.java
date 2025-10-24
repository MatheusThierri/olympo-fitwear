package com.olympofitwear.olympo.olympo_api.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private UUID id;

    private OffsetDateTime orderDate;
    private OrderStatus orderStatus;

    @ManyToOne
    private Client client;

    @OneToOne
    private Payment payment;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

}
