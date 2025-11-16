package com.olympofitwear.olympo.olympo_api.domain.service;

import com.olympofitwear.olympo.olympo_api.api.model.input.ClientModelInput;
import com.olympofitwear.olympo.olympo_api.api.assembler.ClientAssembler;
import com.olympofitwear.olympo.olympo_api.domain.exception.DomainException;
import com.olympofitwear.olympo.olympo_api.domain.exception.EntityNotFoundException;
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
        return clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found"));
    }

    @Transactional
    public Client create(ClientModelInput clientModelInput) {
        Client client = clientAssembler.toEntity(clientModelInput);

        validEmail(client);
        validCpf(client);
        validPassword(client);

        normalizeClient(client);

        return clientRepository.saveAndFlush(client);
    }

    @Transactional
    public Client update(UUID id, ClientModelInput clientModelInput) {
        Client client = findById(id);

        clientAssembler.toExistingClient(clientModelInput, client);

        validEmail(client);
        validCpf(client);
        validPassword(client);

        normalizeClient(client);

        return clientRepository.saveAndFlush(client);
    }

    @Transactional
    public void delete(UUID id) {
        Client client = findById(id);

        clientRepository.delete(client);
    }

    private void validEmail(Client client) {
        boolean emailExists = clientRepository.findClientByEmail(client.getEmail()).filter(p -> !p.equals(client)).isPresent();

        if(emailExists) {
            throw new DomainException("Email already taken");
        }
    }

    public void validCpf(Client client) {
        boolean cpfExists = clientRepository.findClientByCpf(client.getCpf()).filter(p -> !p.equals(client)).isPresent();

        if(cpfExists) {
            throw new DomainException("CPF already taken");
        }
    }

    public void normalizeClient(Client client) {
        client.setCpf(client.getCpf().replace(".", "").replace("-", ""));
        client.setPhone(client.getPhone().replaceAll("\\D", "")); // Only numbers
    }

    public void validPassword(Client client) {
        if(!client.getPassword().matches("^(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).+$")) {
            throw new DomainException("Invalid password: it must include at least one uppercase letter and one special character.");
        }
    }
}
