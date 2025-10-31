package com.olympofitwear.olympo.olympo_api.api.controller;

import com.olympofitwear.olympo.olympo_api.domain.model.Order;
import com.olympofitwear.olympo.olympo_api.domain.repository.OrderRepository;
import com.olympofitwear.olympo.olympo_api.domain.service.OrderRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Order>> findAll() {
        return ResponseEntity.ok(orderRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Order> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(orderRegisterService.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Order> update(@PathVariable UUID id, @RequestBody Order order) {
        return ResponseEntity.ok(orderRegisterService.update(id, order));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        orderRegisterService.delete(id);
    }
}
