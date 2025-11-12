package com.olympofitwear.olympo.olympo_api.api.model.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
public class ClientModelInput {
    @NotBlank
    @Size(max = 200)
    private String name;

    @NotBlank
    @Email
    @Size(max = 200)
    private String email;

    @NotBlank
    @Size(max = 255)
    private String password;

    @NotBlank
    @CPF
    @Size(max = 11)
    private String cpf;

    @NotBlank
    @Size(max = 20)
    private String phone;
}
