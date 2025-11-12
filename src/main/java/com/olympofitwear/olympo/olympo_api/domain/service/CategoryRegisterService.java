package com.olympofitwear.olympo.olympo_api.domain.service;

import com.olympofitwear.olympo.olympo_api.api.model.input.CategoryModelInput;
import com.olympofitwear.olympo.olympo_api.api.assembler.CategoryAssembler;
import com.olympofitwear.olympo.olympo_api.domain.exception.DomainException;
import com.olympofitwear.olympo.olympo_api.domain.model.Category;
import com.olympofitwear.olympo.olympo_api.domain.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
@Service
public class CategoryRegisterService {
    private final CategoryRepository categoryRepository;
    private final CategoryAssembler categoryAssembler;

    public Category findById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(() -> new DomainException("Category not found with ID: " + id));
    }

    @Transactional
    public Category create(CategoryModelInput categoryModelInput) {
        Category category = categoryAssembler.toEntity(categoryModelInput);

        return categoryRepository.saveAndFlush(category);
    }

    @Transactional
    public Category update(UUID id, CategoryModelInput categoryModelInput) {
        Category category = findById(id);

        categoryAssembler.toExistingCategory(categoryModelInput, category);

        return categoryRepository.saveAndFlush(category);
    }

    @Transactional
    public void delete(UUID id) {
        Category category = findById(id);

        categoryRepository.delete(category);
    }
}
