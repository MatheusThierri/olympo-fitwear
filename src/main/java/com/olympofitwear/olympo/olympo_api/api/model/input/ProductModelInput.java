package com.olympofitwear.olympo.olympo_api.api.model.input;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductModelInput {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private CategoryIdModelInput category;
}
