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
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private UUID id;

    private String name;
    private String email;
    private String password;
    private String cpf;
    private String phone;

    @OneToOne
    private Address address;

    @OneToMany(mappedBy = "client")
    private Set<Order> order = new HashSet<>();
}
