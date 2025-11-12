package com.olympofitwear.olympo.olympo_api.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CategoryIdModelInput {
    @NotBlank
    private UUID id;
}
