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
}
