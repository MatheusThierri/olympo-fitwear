package com.olympofitwear.olympo.olympo_api.domain.model;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private UUID id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "category")
    private Set<Product> products = new HashSet<>();
}
