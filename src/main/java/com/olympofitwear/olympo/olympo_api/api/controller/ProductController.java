package com.olympofitwear.olympo.olympo_api.api.controller;

import com.olympofitwear.olympo.olympo_api.domain.model.Product;
import com.olympofitwear.olympo.olympo_api.domain.repository.ProductRepository;
import com.olympofitwear.olympo.olympo_api.domain.service.ProductRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(productRegisterService.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> update(@PathVariable UUID id, Product product) {
        return ResponseEntity.ok(productRegisterService.update(id, product));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        productRegisterService.delete(id);
    }
}
