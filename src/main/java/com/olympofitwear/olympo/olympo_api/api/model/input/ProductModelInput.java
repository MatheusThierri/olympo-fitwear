package com.olympofitwear.olympo.olympo_api.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductModelInput {
    @NotBlank
    @Size(max = 60)
    private String name;

    @NotBlank
    @Size(max = 200)
    private String description;

    @Positive
    private BigDecimal price;

    @PositiveOrZero
    private Integer quantity;

    @Valid
    private CategoryIdModelInput category;
}
