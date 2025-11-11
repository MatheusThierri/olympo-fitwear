package com.olympofitwear.olympo.olympo_api.domain.service;

import com.olympofitwear.olympo.olympo_api.api.model.input.AddressModelInput;
import com.olympofitwear.olympo.olympo_api.api.assembler.AddressAssembler;
import com.olympofitwear.olympo.olympo_api.domain.model.Address;
import com.olympofitwear.olympo.olympo_api.domain.model.Client;
import com.olympofitwear.olympo.olympo_api.domain.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class AddressRegisterService {
    private final ClientRegisterService clientRegisterService;
    private final AddressRepository addressRepository;
    private final AddressAssembler addressAssembler;

    public List<Address> findAllAddresses(UUID clientId) {
        return addressRepository.findByClientId(clientId);
    }

    public Address findById(UUID clientId, UUID addressId) {
        Address address = addressRepository.findById(addressId).get();
        if (!address.getClient().getId().equals(clientId)) {
            System.out.println("Address not found for this client");
        }
        return address;
    }

    @Transactional
    public Address create(UUID id, AddressModelInput addressModelInput) {
        Client client = clientRegisterService.findById(id);
        Address address = addressAssembler.toEntity(addressModelInput);
        client.getAddresses().add(address);
        address.setClient(client);
        return addressRepository.saveAndFlush(address);
    }

    @Transactional
    public Address update(UUID clientId, UUID addressId, AddressModelInput addressModelInput) {
        Address address = addressRepository.findById(addressId).get();
        if (!address.getClient().getId().equals(clientId)) {
            System.out.println("Address not found for this client");
        }
        Client client = clientRegisterService.findById(clientId);
        address.setClient(client);
        address.setId(addressId);
        addressAssembler.toExistingAddress(addressModelInput, address);
        return addressRepository.saveAndFlush(address);
    }

    @Transactional
    public void delete(UUID clientId, UUID addressId) {
        Address address = addressRepository.findById(addressId).get();
        if (!address.getClient().getId().equals(clientId)) {
            System.out.println("Address not found for this client");
        }
        addressRepository.deleteById(addressId);
    }
}
