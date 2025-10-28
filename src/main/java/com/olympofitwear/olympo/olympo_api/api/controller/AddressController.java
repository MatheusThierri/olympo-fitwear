package com.olympofitwear.olympo.olympo_api.api.controller;

import com.olympofitwear.olympo.olympo_api.domain.model.Address;
import com.olympofitwear.olympo.olympo_api.domain.repository.AddressRepository;
import com.olympofitwear.olympo.olympo_api.domain.service.AddressRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressRegisterService addressRegisterService;
    private final AddressRepository addressRepository;

    @GetMapping
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @GetMapping("{id}")
    public Address findById(@PathVariable UUID id) {
        return addressRegisterService.findById(id);
    }
}
