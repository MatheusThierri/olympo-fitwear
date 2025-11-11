package com.olympofitwear.olympo.olympo_api.api.model.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressModelInput {
    private String street;
    private String neighborhood;
    private String number;
    private String cep;
}
