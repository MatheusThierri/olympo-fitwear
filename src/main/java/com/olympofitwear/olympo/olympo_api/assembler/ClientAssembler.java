package com.olympofitwear.olympo.olympo_api.assembler;

import com.olympofitwear.olympo.olympo_api.api.model.input.ClientModelInput;
import com.olympofitwear.olympo.olympo_api.api.model.output.ClientRepresentationModel;
import com.olympofitwear.olympo.olympo_api.domain.model.Client;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ClientAssembler {
    private final ModelMapper modelMapper;

    public Client toEntity(ClientModelInput clientModelInput) {
        return modelMapper.map(clientModelInput, Client.class);
    }

    public ClientRepresentationModel toModel(Client client) {
        return modelMapper.map(client, ClientRepresentationModel.class);
    }

    public List<ClientRepresentationModel> toCollectionModel(List<Client> clients) {
        return clients.stream()
                      .map(this::toModel)
                      .collect(Collectors.toList());
    }
}
