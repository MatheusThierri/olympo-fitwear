package com.olympofitwear.olympo.olympo_api.domain.service;

import com.olympofitwear.olympo.olympo_api.api.model.input.AddressModelInput;
import com.olympofitwear.olympo.olympo_api.api.assembler.AddressAssembler;
import com.olympofitwear.olympo.olympo_api.domain.exception.EntityNotFoundException;
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
        Client client = clientRegisterService.findById(clientId);

        return addressRepository.findByClientId(client.getId());
    }

    public Address findById(UUID clientId, UUID addressId) {
        Client client = clientRegisterService.findById(clientId);

        Address address = findValidAddress(client.getId(), addressId);

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
        Client client = clientRegisterService.findById(clientId);

        Address address = findValidAddress(clientId, addressId);
        address.setClient(client);
        address.setId(addressId);
        addressAssembler.toExistingAddress(addressModelInput, address);

        return addressRepository.saveAndFlush(address);
    }

    @Transactional
    public void delete(UUID clientId, UUID addressId) {
        Client client = clientRegisterService.findById(clientId);

        Address address = findValidAddress(client.getId(), addressId);

        addressRepository.delete(address);
    }

    private Address findValidAddress(UUID clientId, UUID addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new EntityNotFoundException("Address not found"));

        if (!address.getClient().getId().equals(clientId)) {
            throw new EntityNotFoundException("Address not found for this client");
        }

        return address;
    }
}
