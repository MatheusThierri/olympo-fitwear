package com.olympofitwear.olympo.olympo_api.api.controller;

import com.olympofitwear.olympo.olympo_api.api.model.input.CategoryModelInput;
import com.olympofitwear.olympo.olympo_api.api.model.output.CategoryRepresentationModel;
import com.olympofitwear.olympo.olympo_api.api.assembler.CategoryAssembler;
import com.olympofitwear.olympo.olympo_api.domain.repository.CategoryRepository;
import com.olympofitwear.olympo.olympo_api.domain.service.CategoryRegisterService;
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
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryRegisterService categoryRegisterService;
    private final CategoryRepository categoryRepository;
    private final CategoryAssembler categoryAssembler;

    @GetMapping
    public ResponseEntity<List<CategoryRepresentationModel>> findAll() {
        return ResponseEntity.ok(categoryAssembler.toCollectionModel(categoryRepository.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryRepresentationModel> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(categoryAssembler.toModel(categoryRegisterService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<CategoryRepresentationModel> create(@Valid @RequestBody CategoryModelInput categoryModelInput) {
        CategoryRepresentationModel categoryRepresentationModel = categoryAssembler.toModel(categoryRegisterService.create(categoryModelInput));

        URI location = URI.create(String.format("/categories/%s", categoryRepresentationModel.getId()));

        return ResponseEntity.created(location).body(categoryRepresentationModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryRepresentationModel> update(@PathVariable UUID id, @Valid @RequestBody CategoryModelInput categoryModelInput) {
        return ResponseEntity.ok(categoryAssembler.toModel(categoryRegisterService.update(id, categoryModelInput)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        categoryRegisterService.delete(id);
    }
}
