package com.olympofitwear.olympo.olympo_api.api.controller;

import com.olympofitwear.olympo.olympo_api.api.model.input.OrderModelInput;
import com.olympofitwear.olympo.olympo_api.api.model.output.OrderRepresentationModel;
import com.olympofitwear.olympo.olympo_api.assembler.OrderAssembler;
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
    private final OrderAssembler orderAssembler;

    @GetMapping
    public ResponseEntity<List<OrderRepresentationModel>> findAll() {
        return ResponseEntity.ok(orderAssembler.toCollectionModel(orderRepository.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderRepresentationModel> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(orderAssembler.toModel(orderRegisterService.findById(id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderRepresentationModel create(@RequestBody OrderModelInput orderModelInput) {
        return orderAssembler.toModel(orderRegisterService.create(orderModelInput));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderRepresentationModel> update(@PathVariable UUID id, @RequestBody OrderModelInput orderModelInput) {
        return ResponseEntity.ok(orderAssembler.toModel(orderRegisterService.update(id, orderModelInput)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        orderRegisterService.delete(id);
    }
}
