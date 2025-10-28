package com.olympofitwear.olympo.olympo_api.domain.service;

import com.olympofitwear.olympo.olympo_api.domain.model.Address;
import com.olympofitwear.olympo.olympo_api.domain.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class AddressRegisterService {
    private final AddressRepository addressRepository;

    public Address findById(UUID id) {
        return addressRepository.findById(id).get();
    }
}
