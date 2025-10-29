package com.olympofitwear.olympo.olympo_api.api.controller;

import com.olympofitwear.olympo.olympo_api.domain.model.Address;
import com.olympofitwear.olympo.olympo_api.domain.repository.AddressRepository;
import com.olympofitwear.olympo.olympo_api.domain.service.AddressRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/clients/{clientId}/address")
public class AddressController {
    private final AddressRegisterService addressRegisterService;
    private final AddressRepository addressRepository;

    @GetMapping
    public Set<Address> findAllAddress(@PathVariable UUID clientId) {
        return addressRegisterService.findAllAddress(clientId);
    }

    @GetMapping("/{addressId}")
    public Address findById(@PathVariable UUID clientId, @PathVariable UUID addressId) {
        return addressRegisterService.findById(addressId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Address create(@PathVariable UUID clientId, @RequestBody Address address) {
        return addressRegisterService.create(clientId, address);
    }

    @PutMapping("/{addressId}")
    public Address update(@PathVariable UUID clientId, @PathVariable UUID addressId, @RequestBody Address address) {
        return addressRegisterService.update(addressId, address);
    }

    @DeleteMapping("/{addressId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID clientId, @PathVariable UUID addressId) {
        addressRegisterService.delete(addressId);
    }
}
