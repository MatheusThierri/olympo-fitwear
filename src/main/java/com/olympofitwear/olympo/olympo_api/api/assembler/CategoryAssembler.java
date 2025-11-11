package com.olympofitwear.olympo.olympo_api.api.assembler;

import com.olympofitwear.olympo.olympo_api.api.model.input.CategoryModelInput;
import com.olympofitwear.olympo.olympo_api.api.model.output.CategoryRepresentationModel;
import com.olympofitwear.olympo.olympo_api.domain.model.Category;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class CategoryAssembler {
    private final ModelMapper modelMapper;

    public Category toEntity(CategoryModelInput categoryModelInput) {
        return modelMapper.map(categoryModelInput, Category.class);
    }

    public CategoryRepresentationModel toModel(Category category) {
        return modelMapper.map(category, CategoryRepresentationModel.class);
    }

    public List<CategoryRepresentationModel> toCollectionModel(List<Category> categories) {
        return categories.stream()
                         .map(this::toModel)
                         .collect(Collectors.toList());
    }

    public void toExistingCategory(CategoryModelInput categoryModelInput, Category category) {
        modelMapper.map(categoryModelInput, category);
    }
}
