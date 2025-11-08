package com.olympofitwear.olympo.olympo_api.domain.service;

import com.olympofitwear.olympo.olympo_api.api.model.input.CategoryModelInput;
import com.olympofitwear.olympo.olympo_api.assembler.CategoryAssembler;
import com.olympofitwear.olympo.olympo_api.domain.model.Category;
import com.olympofitwear.olympo.olympo_api.domain.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class CategoryRegisterService {
    private final CategoryRepository categoryRepository;
    private final CategoryAssembler categoryAssembler;

    public Category findById(UUID id) {
        return categoryRepository.findById(id).get();
    }

    public Category create(CategoryModelInput categoryModelInput) {
        Category category = categoryAssembler.toEntity(categoryModelInput);
        return categoryRepository.saveAndFlush(category);
    }

    public Category update(UUID id, CategoryModelInput categoryModelInput) {
        Category category = categoryAssembler.toEntity(categoryModelInput);
        category.setId(id);
        return categoryRepository.saveAndFlush(category);
    }

    public void delete(UUID id) {
        categoryRepository.deleteById(id);
    }
}
