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

    public Product update(UUID id, Product product) {
        product.setId(id);
        return productRepository.saveAndFlush(product);
    }

    public void delete(UUID id) {
        productRepository.deleteById(id);
    }
}
