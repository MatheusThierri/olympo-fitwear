package com.olympofitwear.olympo.olympo_api.api.controller;

import com.olympofitwear.olympo.olympo_api.domain.model.Category;
import com.olympofitwear.olympo.olympo_api.domain.repository.CategoryRepository;
import com.olympofitwear.olympo.olympo_api.domain.service.CategoryRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryRegisterService categoryRegisterService;
    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @GetMapping("{id}")
    public Category findById(@PathVariable UUID id) {
        return categoryRegisterService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category create(@RequestBody Category category) {
        return categoryRegisterService.create(category);
    }

    @PutMapping("{id}")
    public Category update(@PathVariable UUID id, Category category) {
        return categoryRegisterService.update(id, category);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        categoryRegisterService.delete(id);
    }
}
