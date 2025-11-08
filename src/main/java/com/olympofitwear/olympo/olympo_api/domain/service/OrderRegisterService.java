package com.olympofitwear.olympo.olympo_api.domain.service;

import com.olympofitwear.olympo.olympo_api.api.model.input.OrderModelInput;
import com.olympofitwear.olympo.olympo_api.assembler.OrderAssembler;
import com.olympofitwear.olympo.olympo_api.domain.model.Client;
import com.olympofitwear.olympo.olympo_api.domain.model.Order;
import com.olympofitwear.olympo.olympo_api.domain.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class OrderRegisterService {
    private final OrderRepository orderRepository;
    private final OrderAssembler orderAssembler;
    private final ClientRegisterService clientRegisterService;

    public Order findById(UUID id) {
        return orderRepository.findById(id).get();
    }

    public Order update(UUID id, OrderModelInput orderModelInput) {
        Order order = orderAssembler.toEntity(orderModelInput);
        order.setId(id);
        return orderRepository.saveAndFlush(order);
    }

    public Order create(OrderModelInput orderModelInput) {
        Order order = orderAssembler.toEntity(orderModelInput);
        Client client = clientRegisterService.findById(orderModelInput.getClient().getId());
        order.setClient(client);
        return orderRepository.saveAndFlush(order);
    }

    public void delete(UUID id) {
        orderRepository.deleteById(id);
    }
}
