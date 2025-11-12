package com.olympofitwear.olympo.olympo_api.domain.service;

import com.olympofitwear.olympo.olympo_api.api.model.input.ClientModelInput;
import com.olympofitwear.olympo.olympo_api.api.assembler.ClientAssembler;
import com.olympofitwear.olympo.olympo_api.domain.exception.DomainException;
import com.olympofitwear.olympo.olympo_api.domain.model.Client;
import com.olympofitwear.olympo.olympo_api.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
@Service
public class ClientRegisterService {
    private final ClientRepository clientRepository;
    private final ClientAssembler clientAssembler;

    public Client findById(UUID id) {
        return clientRepository.findById(id).orElseThrow(() -> new DomainException("Client not found with ID: " + id));
    }

    @Transactional
    public Client create(ClientModelInput clientModelInput) {
        Client client = clientAssembler.toEntity(clientModelInput);

        return clientRepository.saveAndFlush(client);
    }

    @Transactional
    public Client update(UUID id, ClientModelInput clientModelInput) {
        Client client = findById(id);

        clientAssembler.toExistingClient(clientModelInput, client);

        return clientRepository.saveAndFlush(client);
    }

    @Transactional
    public void delete(UUID id) {
        Client client = findById(id);

        clientRepository.delete(client);
    }
}
