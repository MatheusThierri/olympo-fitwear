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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)")
    @EqualsAndHashCode.Include
    private UUID id;

    private String name;
    private String email;
    private String password;
    private String cpf;
    private String phone;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(mappedBy = "client")
    private Set<Order> orders = new HashSet<>();
}
