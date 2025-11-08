package com.olympofitwear.olympo.olympo_api.domain.service;

import com.olympofitwear.olympo.olympo_api.api.model.input.AddressModelInput;
import com.olympofitwear.olympo.olympo_api.assembler.AddressAssembler;
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
    private final AddressAssembler addressAssembler;

    public Set<Address> findAllAddresses(UUID id) {
        return clientRegisterService.findById(id).getAddress();
    }

    public Address findById(UUID id) {
        return addressRepository.findById(id).get();
    }

    public Address create(UUID id, AddressModelInput addressModelInput) {
        Client client = clientRegisterService.findById(id);
        Address address = addressAssembler.toEntity(addressModelInput);
        client.getAddress().add(address);
        address.setClient(client);
        return addressRepository.saveAndFlush(address);
    }

    public Address update(UUID clientId, UUID addressId, AddressModelInput addressModelInput) {
        Address address = addressAssembler.toEntity(addressModelInput);
        Client client = clientRegisterService.findById(clientId);
        address.setClient(client);
        address.setId(addressId);
        return addressRepository.saveAndFlush(address);
    }

    public void delete(UUID id) {
        addressRepository.deleteById(id);
    }
}
