package com.olympofitwear.olympo.olympo_api.domain.service;

import com.olympofitwear.olympo.olympo_api.domain.model.Order;
import com.olympofitwear.olympo.olympo_api.domain.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class OrderRegisterService {
    private final OrderRepository orderRepository;

    public Order findById(UUID id) {
        return orderRepository.findById(id).get();
    }

    public Order update(UUID id, Order order) {
        order.setId(id);
        return orderRepository.saveAndFlush(order);
    }

    public void delete(UUID id) {
        orderRepository.deleteById(id);
    }
}
