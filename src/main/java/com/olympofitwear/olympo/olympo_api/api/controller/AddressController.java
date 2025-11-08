package com.olympofitwear.olympo.olympo_api.api.controller;

import com.olympofitwear.olympo.olympo_api.api.model.input.AddressModelInput;
import com.olympofitwear.olympo.olympo_api.api.model.output.AddressRepresentationModel;
import com.olympofitwear.olympo.olympo_api.assembler.AddressAssembler;
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
@RequestMapping("/clients/{clientId}/addresses")
public class AddressController {
    private final AddressRegisterService addressRegisterService;
    private final AddressRepository addressRepository;
    private final AddressAssembler addressAssembler;

    @GetMapping
    public ResponseEntity<Set<AddressRepresentationModel>> findAllAddresses(@PathVariable UUID clientId) {
        return ResponseEntity.ok(addressAssembler.toCollectionModel(addressRegisterService.findAllAddresses(clientId)));
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressRepresentationModel> findById(@PathVariable UUID clientId, @PathVariable UUID addressId) {
        return ResponseEntity.ok(addressAssembler.toModel(addressRegisterService.findById(addressId)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressRepresentationModel create(@PathVariable UUID clientId, @RequestBody AddressModelInput addressModelInput) {
        return addressAssembler.toModel(addressRegisterService.create(clientId, addressModelInput));
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressRepresentationModel> update(@PathVariable UUID clientId, @PathVariable UUID addressId, @RequestBody AddressModelInput addressModelInput) {
        return ResponseEntity.ok(addressAssembler.toModel(addressRegisterService.update(clientId, addressId, addressModelInput)));
    }

    @DeleteMapping("/{addressId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID clientId, @PathVariable UUID addressId) {
        addressRegisterService.delete(addressId);
    }
}
