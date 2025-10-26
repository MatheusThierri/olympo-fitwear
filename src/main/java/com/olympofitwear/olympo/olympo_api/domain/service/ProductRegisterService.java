package com.olympofitwear.olympo.olympo_api.domain.service;

import com.olympofitwear.olympo.olympo_api.domain.model.Product;
import com.olympofitwear.olympo.olympo_api.domain.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class ProductRegisterService {
    private final ProductRepository productRepository;

    public Product findById(UUID id) {
        return productRepository.findById(id).get();
    }
}
