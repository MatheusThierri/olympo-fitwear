package com.olympofitwear.olympo.olympo_api.api.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressModelInput {
    @NotBlank
    @Size(max = 60)
    private String street;

    @NotBlank
    @Size(max = 40)
    private String neighborhood;

    @NotBlank
    @Size(max = 10)
    private String number;

    @NotBlank
    @Size(max = 20)
    private String cep;
}
