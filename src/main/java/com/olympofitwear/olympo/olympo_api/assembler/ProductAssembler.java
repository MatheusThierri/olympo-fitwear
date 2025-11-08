package com.olympofitwear.olympo.olympo_api.assembler;

import com.olympofitwear.olympo.olympo_api.api.model.input.ProductModelInput;
import com.olympofitwear.olympo.olympo_api.api.model.output.ProductRepresentationModel;
import com.olympofitwear.olympo.olympo_api.domain.model.Product;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ProductAssembler {
    private final ModelMapper modelMapper;

    public Product toEntity(ProductModelInput productModelInput) {
        return modelMapper.map(productModelInput, Product.class);
    }

    public ProductRepresentationModel toModel(Product product) {
        return modelMapper.map(product, ProductRepresentationModel.class);
    }

    public List<ProductRepresentationModel> toCollectionModel(List<Product> products) {
        return products.stream()
                       .map(this::toModel)
                       .collect(Collectors.toList());
    }
}
