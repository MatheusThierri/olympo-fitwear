package com.olympofitwear.olympo.olympo_api.domain.service;

import com.olympofitwear.olympo.olympo_api.domain.model.Client;
import com.olympofitwear.olympo.olympo_api.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class ClientRegisterService {
    private final ClientRepository clientRepository;

    public Client findById(UUID id) {
        return clientRepository.findById(id).get();
    }
}
