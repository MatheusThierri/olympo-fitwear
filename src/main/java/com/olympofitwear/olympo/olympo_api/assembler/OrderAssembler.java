package com.olympofitwear.olympo.olympo_api.assembler;

import com.olympofitwear.olympo.olympo_api.api.model.input.OrderModelInput;
import com.olympofitwear.olympo.olympo_api.api.model.output.OrderRepresentationModel;
import com.olympofitwear.olympo.olympo_api.domain.model.Order;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class OrderAssembler {
    private final ModelMapper modelMapper;

    public Order toEntity(OrderModelInput orderModelInput) {
        return modelMapper.map(orderModelInput, Order.class);
    }

    public OrderRepresentationModel toModel(Order order) {
        return modelMapper.map(order, OrderRepresentationModel.class);
    }

    public List<OrderRepresentationModel> toCollectionModel(List<Order> orders) {
        return orders.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
