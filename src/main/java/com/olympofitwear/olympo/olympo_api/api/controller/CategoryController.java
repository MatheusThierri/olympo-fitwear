package com.olympofitwear.olympo.olympo_api.api.controller;

import com.olympofitwear.olympo.olympo_api.domain.model.Category;
import com.olympofitwear.olympo.olympo_api.domain.repository.CategoryRepository;
import com.olympofitwear.olympo.olympo_api.domain.service.CategoryRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
