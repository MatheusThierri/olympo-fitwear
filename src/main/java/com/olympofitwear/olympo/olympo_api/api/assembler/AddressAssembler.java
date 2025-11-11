package com.olympofitwear.olympo.olympo_api.api.assembler;

import com.olympofitwear.olympo.olympo_api.api.model.input.AddressModelInput;
import com.olympofitwear.olympo.olympo_api.api.model.output.AddressRepresentationModel;
import com.olympofitwear.olympo.olympo_api.domain.model.Address;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class AddressAssembler {
    private final ModelMapper modelMapper;

    public Address toEntity(AddressModelInput addressModelInput) {
        return modelMapper.map(addressModelInput, Address.class);
    }

    public AddressRepresentationModel toModel(Address address) {
        return modelMapper.map(address, AddressRepresentationModel.class);
    }

    public List<AddressRepresentationModel> toCollectionModel(List<Address> addresses) {
        return addresses.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public void toExistingAddress(AddressModelInput addressModelInput, Address address) {
        modelMapper.map(addressModelInput, address);
    }
}
