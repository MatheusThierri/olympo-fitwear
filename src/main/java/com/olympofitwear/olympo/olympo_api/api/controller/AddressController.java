package com.olympofitwear.olympo.olympo_api.api.controller;

import com.olympofitwear.olympo.olympo_api.api.model.input.AddressModelInput;
import com.olympofitwear.olympo.olympo_api.api.model.output.AddressRepresentationModel;
import com.olympofitwear.olympo.olympo_api.api.assembler.AddressAssembler;
import com.olympofitwear.olympo.olympo_api.domain.service.AddressRegisterService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/clients/{clientId}/addresses")
public class AddressController {
    private final AddressRegisterService addressRegisterService;
    private final AddressAssembler addressAssembler;

    @GetMapping
    public ResponseEntity<List<AddressRepresentationModel>> findAllAddresses(@PathVariable UUID clientId) {
        return ResponseEntity.ok(addressAssembler.toCollectionModel(addressRegisterService.findAllAddresses(clientId)));
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressRepresentationModel> findById(@PathVariable UUID clientId, @PathVariable UUID addressId) {
        return ResponseEntity.ok(addressAssembler.toModel(addressRegisterService.findById(clientId, addressId)));
    }

    @PostMapping
    public ResponseEntity<AddressRepresentationModel> create(@PathVariable UUID clientId, @Valid @RequestBody AddressModelInput addressModelInput) {
        AddressRepresentationModel addressRepresentationModel = addressAssembler.toModel(addressRegisterService.create(clientId, addressModelInput));

        URI location = URI.create(String.format("/clients/%s/addresses/%s", clientId, addressRepresentationModel.getId()));

        return ResponseEntity.created(location).body(addressRepresentationModel);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressRepresentationModel> update(@PathVariable UUID clientId, @PathVariable UUID addressId, @Valid @RequestBody AddressModelInput addressModelInput) {
        return ResponseEntity.ok(addressAssembler.toModel(addressRegisterService.update(clientId, addressId, addressModelInput)));
    }

    @DeleteMapping("/{addressId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID clientId, @PathVariable UUID addressId) {
        addressRegisterService.delete(clientId, addressId);
    }
}
