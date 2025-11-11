package com.olympofitwear.olympo.olympo_api.api.controller;

import com.olympofitwear.olympo.olympo_api.api.model.input.ClientModelInput;
import com.olympofitwear.olympo.olympo_api.api.model.output.ClientRepresentationModel;
import com.olympofitwear.olympo.olympo_api.api.assembler.ClientAssembler;
import com.olympofitwear.olympo.olympo_api.domain.repository.ClientRepository;
import com.olympofitwear.olympo.olympo_api.domain.service.ClientRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientRegisterService clientRegisterService;
    private final ClientRepository clientRepository;
    private final ClientAssembler clientAssembler;

    @GetMapping
    public ResponseEntity<List<ClientRepresentationModel>> findAll() {
        return ResponseEntity.ok(clientAssembler.toCollectionModel(clientRepository.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientRepresentationModel> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(clientAssembler.toModel(clientRegisterService.findById(id)));
    }

    @PostMapping()
    public ResponseEntity<ClientRepresentationModel> create(@RequestBody ClientModelInput clientModelInput) {
        ClientRepresentationModel clientRepresentationModel = clientAssembler.toModel(clientRegisterService.create(clientModelInput));

        URI location = URI.create(String.format("/clients/%s", clientRepresentationModel.getId()));

        return ResponseEntity.created(location).body(clientRepresentationModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientRepresentationModel> update(@PathVariable UUID id, @RequestBody ClientModelInput clientModelInput) {
        return ResponseEntity.ok(clientAssembler.toModel(clientRegisterService.update(id, clientModelInput)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        clientRegisterService.delete(id);
    }
}
