package com.olympofitwear.olympo.olympo_api.domain.service;

import com.olympofitwear.olympo.olympo_api.api.model.input.ProductModelInput;
import com.olympofitwear.olympo.olympo_api.api.assembler.ProductAssembler;
import com.olympofitwear.olympo.olympo_api.domain.model.Category;
import com.olympofitwear.olympo.olympo_api.domain.model.Product;
import com.olympofitwear.olympo.olympo_api.domain.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Product update(UUID id, ProductModelInput productModelInput) {
        Product product = findById(id);
        Category category = categoryRegisterService.findById(productModelInput.getCategory().getId());
        product.setCategory(category);
        productAssembler.toExistingProduct(productModelInput, product);

        return productRepository.saveAndFlush(product);
    }

    @Transactional
    public Product create(ProductModelInput productModelInput) {
        Product product = productAssembler.toEntity(productModelInput);
        Category category = categoryRegisterService.findById(product.getCategory().getId());
        product.setCategory(category);

        return productRepository.saveAndFlush(product);
    }

    @Transactional
    public void delete(UUID id) {
        productRepository.deleteById(id);
    }
}
