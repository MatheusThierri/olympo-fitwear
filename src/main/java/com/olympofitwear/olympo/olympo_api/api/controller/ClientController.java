package com.olympofitwear.olympo.olympo_api.api.controller;

import com.olympofitwear.olympo.olympo_api.domain.model.Client;
import com.olympofitwear.olympo.olympo_api.domain.repository.ClientRepository;
import com.olympofitwear.olympo.olympo_api.domain.service.ClientRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientRegisterService clientRegisterService;
    private final ClientRepository clientRepository;

    @GetMapping
    public ResponseEntity<List<Client>> findAll() {
        return ResponseEntity.ok(clientRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Client> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(clientRegisterService.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Client> update(@PathVariable UUID id, @RequestBody Client client) {
        return ResponseEntity.ok(clientRegisterService.update(id, client));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        clientRegisterService.delete(id);
    }
}
