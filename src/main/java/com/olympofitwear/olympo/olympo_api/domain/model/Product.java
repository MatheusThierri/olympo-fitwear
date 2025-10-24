package com.olympofitwear.olympo.olympo_api.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private UUID id;

    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> items = new HashSet<>();
}
