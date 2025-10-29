package com.olympofitwear.olympo.olympo_api.domain.service;

import com.olympofitwear.olympo.olympo_api.domain.model.Address;
import com.olympofitwear.olympo.olympo_api.domain.model.Client;
import com.olympofitwear.olympo.olympo_api.domain.repository.AddressRepository;
import com.olympofitwear.olympo.olympo_api.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Service
public class AddressRegisterService {
    private final ClientRegisterService clientRegisterService;
    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;

    public Set<Address> findAllAddress(UUID id) {
        return clientRegisterService.findById(id).getAddress();
    }

    public Address findById(UUID id) {
        return addressRepository.findById(id).get();
    }

    public Address create(UUID id, Address address) {
        Client client = clientRegisterService.findById(id);
        client.getAddress().add(address);
        clientRepository.saveAndFlush(client);
        return address;
    }

    public Address update(UUID id, Address address) {
        address.setId(id);
        return addressRepository.saveAndFlush(address);
    }

    public void delete(UUID id) {
        clientRepository.deleteById(id);
    }
}
