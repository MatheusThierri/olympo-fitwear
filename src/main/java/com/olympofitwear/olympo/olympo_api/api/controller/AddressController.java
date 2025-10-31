package com.olympofitwear.olympo.olympo_api.api.controller;

import com.olympofitwear.olympo.olympo_api.domain.model.Address;
import com.olympofitwear.olympo.olympo_api.domain.repository.AddressRepository;
import com.olympofitwear.olympo.olympo_api.domain.service.AddressRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Set<Address>> findAllAddress(@PathVariable UUID clientId) {
        return ResponseEntity.ok(addressRegisterService.findAllAddress(clientId));
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<Address> findById(@PathVariable UUID clientId, @PathVariable UUID addressId) {
        return ResponseEntity.ok(addressRegisterService.findById(addressId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Address create(@PathVariable UUID clientId, @RequestBody Address address) {
        return addressRegisterService.create(clientId, address);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<Address> update(@PathVariable UUID clientId, @PathVariable UUID addressId, @RequestBody Address address) {
        return ResponseEntity.ok(addressRegisterService.update(addressId, address));
    }

    @DeleteMapping("/{addressId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID clientId, @PathVariable UUID addressId) {
        addressRegisterService.delete(addressId);
    }
}
