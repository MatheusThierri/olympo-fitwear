package com.olympofitwear.olympo.olympo_api.api.controller;

import com.olympofitwear.olympo.olympo_api.api.model.input.ProductModelInput;
import com.olympofitwear.olympo.olympo_api.api.model.output.ProductRepresentationModel;
import com.olympofitwear.olympo.olympo_api.api.assembler.ProductAssembler;
import com.olympofitwear.olympo.olympo_api.domain.model.Category;
import com.olympofitwear.olympo.olympo_api.domain.model.Product;
import com.olympofitwear.olympo.olympo_api.domain.repository.ProductRepository;
import com.olympofitwear.olympo.olympo_api.domain.service.ProductRegisterService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRegisterService productRegisterService;
    private final ProductRepository productRepository;
    private final ProductAssembler productAssembler;

    @GetMapping
    public ResponseEntity<List<ProductRepresentationModel>> findAll() {
        return ResponseEntity.ok(productAssembler.toCollectionModel(productRepository.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductRepresentationModel> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(productAssembler.toModel(productRegisterService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ProductRepresentationModel> create(@Valid @RequestBody ProductModelInput productModelInput) {
        ProductRepresentationModel productRepresentationModel = productAssembler.toModel(productRegisterService.create(productModelInput));

        URI location = URI.create(String.format("/products/%s", productRepresentationModel.getId()));

        return ResponseEntity.created(location).body(productRepresentationModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductRepresentationModel> update(@PathVariable UUID id, @Valid @RequestBody ProductModelInput productModelInput) {
        return ResponseEntity.ok(productAssembler.toModel(productRegisterService.update(id, productModelInput)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        productRegisterService.delete(id);
    }
}
