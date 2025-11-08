package com.olympofitwear.olympo.olympo_api.domain.service;

import com.olympofitwear.olympo.olympo_api.api.model.input.ClientModelInput;
import com.olympofitwear.olympo.olympo_api.assembler.ClientAssembler;
import com.olympofitwear.olympo.olympo_api.domain.model.Client;
import com.olympofitwear.olympo.olympo_api.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class ClientRegisterService {
    private final ClientRepository clientRepository;
    private final ClientAssembler clientAssembler;

    public Client findById(UUID id) {
        return clientRepository.findById(id).get();
    }

    public void delete(UUID id) {
        clientRepository.deleteById(id);
    }

    public Client create(ClientModelInput clientModelInput) {
        Client client = new Client();
        client = clientAssembler.toEntity(clientModelInput);
        return clientRepository.saveAndFlush(client);
    }

    public Client update(UUID id, ClientModelInput clientModelInput) {
        Client client = new Client();
        client = clientAssembler.toEntity(clientModelInput);
        client.setId(id);

        return clientRepository.saveAndFlush(client);
    }
}
