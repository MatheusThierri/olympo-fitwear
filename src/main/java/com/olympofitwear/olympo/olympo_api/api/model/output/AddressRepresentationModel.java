package com.olympofitwear.olympo.olympo_api.api.model.output;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AddressRepresentationModel {
    private UUID id;
    private String street;
    private String neighborhood;
    private String number;
    private String cep;
    private ClientResumeModel client;
}
