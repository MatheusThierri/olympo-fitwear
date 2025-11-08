package com.olympofitwear.olympo.olympo_api.api.model.input;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ClientModelInput {
    private String name;
    private String email;
    private String password;
    private String cpf;
    private String phone;
}
