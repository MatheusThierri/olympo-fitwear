package com.olympofitwear.olympo.olympo_api.domain.service;

import com.olympofitwear.olympo.olympo_api.api.model.input.ProductModelInput;
import com.olympofitwear.olympo.olympo_api.assembler.ProductAssembler;
import com.olympofitwear.olympo.olympo_api.domain.model.Category;
import com.olympofitwear.olympo.olympo_api.domain.model.Product;
import com.olympofitwear.olympo.olympo_api.domain.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class ProductRegisterService {
    private final ProductRepository productRepository;
    private final CategoryRegisterService categoryRegisterService;
    private final ProductAssembler productAssembler;

    public Product findById(UUID id) {
        return productRepository.findById(id).get();
    }

    public Product update(UUID id, ProductModelInput productModelInput) {
        Product product = productAssembler.toEntity(productModelInput);
        product.setId(id);
        Category category = categoryRegisterService.findById(product.getCategory().getId());
        product.setCategory(category);
        return productRepository.saveAndFlush(product);
    }

    public Product create(ProductModelInput productModelInput) {
        Product product = productAssembler.toEntity(productModelInput);
        Category category = categoryRegisterService.findById(product.getCategory().getId());
        product.setCategory(category);
        return productRepository.saveAndFlush(product);
    }

    public void delete(UUID id) {
        productRepository.deleteById(id);
    }
}
