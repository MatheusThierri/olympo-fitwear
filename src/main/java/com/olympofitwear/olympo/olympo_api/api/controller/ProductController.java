package com.olympofitwear.olympo.olympo_api.api.controller;

import com.olympofitwear.olympo.olympo_api.domain.model.Product;
import com.olympofitwear.olympo.olympo_api.domain.repository.ProductRepository;
import com.olympofitwear.olympo.olympo_api.domain.service.ProductRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRegisterService productRegisterService;
    private final ProductRepository productRepository;

    @GetMapping
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @GetMapping("{id}")
    public Product findById(@PathVariable UUID id) {
        return productRegisterService.findById(id);
    }

    @PutMapping("{id}")
    public Product update(@PathVariable UUID id, Product product) {
        return productRegisterService.update(id, product);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable UUID id) {
        productRegisterService.delete(id);
    }
}
