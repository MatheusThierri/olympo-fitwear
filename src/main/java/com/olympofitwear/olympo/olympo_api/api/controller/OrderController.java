package com.olympofitwear.olympo.olympo_api.api.controller;

import com.olympofitwear.olympo.olympo_api.domain.model.Order;
import com.olympofitwear.olympo.olympo_api.domain.repository.OrderRepository;
import com.olympofitwear.olympo.olympo_api.domain.service.OrderRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderRegisterService orderRegisterService;
    private final OrderRepository orderRepository;

    @GetMapping
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @GetMapping("{id}")
    public Order findById(@PathVariable UUID id) {
        return orderRegisterService.findById(id);
    }

    @PutMapping("{id}")
    public Order update(@PathVariable UUID id, @RequestBody Order order) {
        return orderRegisterService.update(id, order);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable UUID id) {
        orderRegisterService.delete(id);
    }
}
