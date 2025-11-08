package com.olympofitwear.olympo.olympo_api.common;

import com.olympofitwear.olympo.olympo_api.api.model.output.ClientRepresentationModel;
import com.olympofitwear.olympo.olympo_api.domain.model.Client;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Client.class, ClientRepresentationModel.class)
                .addMappings(mapper -> {
                    mapper.map(Client::getId, ClientRepresentationModel::setId);
                    mapper.map(Client::getName, ClientRepresentationModel::setName);
                });

        return modelMapper;
    }
}
