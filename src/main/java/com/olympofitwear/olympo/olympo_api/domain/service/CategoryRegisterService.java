package com.olympofitwear.olympo.olympo_api.domain.service;

import com.olympofitwear.olympo.olympo_api.domain.model.Category;
import com.olympofitwear.olympo.olympo_api.domain.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class CategoryRegisterService {
    private final CategoryRepository categoryRepository;

    public Category findById(UUID id) {
        return categoryRepository.findById(id).get();
    }

    public Category create(Category category) {
        return categoryRepository.saveAndFlush(category);
    }

    public Category update(UUID id, Category category) {
        category.setId(id);
        return categoryRepository.saveAndFlush(category);
    }

    public void delete(UUID id) {
        categoryRepository.deleteById(id);
    }
}
