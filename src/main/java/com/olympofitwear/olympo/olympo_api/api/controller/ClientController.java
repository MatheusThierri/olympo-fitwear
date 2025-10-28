package com.olympofitwear.olympo.olympo_api.api.controller;

import com.olympofitwear.olympo.olympo_api.domain.model.Client;
import com.olympofitwear.olympo.olympo_api.domain.repository.ClientRepository;
import com.olympofitwear.olympo.olympo_api.domain.service.ClientRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientRegisterService clientRegisterService;
    private final ClientRepository clientRepository;

    @GetMapping
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @GetMapping("{id}")
    public Client findById(@PathVariable UUID id) {
        return clientRegisterService.findById(id);
    }
}
