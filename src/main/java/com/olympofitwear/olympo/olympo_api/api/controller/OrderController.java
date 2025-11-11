package com.olympofitwear.olympo.olympo_api.api.controller;

import com.olympofitwear.olympo.olympo_api.api.model.input.OrderModelInput;
import com.olympofitwear.olympo.olympo_api.api.model.output.OrderRepresentationModel;
import com.olympofitwear.olympo.olympo_api.api.assembler.OrderAssembler;
import com.olympofitwear.olympo.olympo_api.domain.repository.OrderRepository;
import com.olympofitwear.olympo.olympo_api.domain.service.OrderRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/clients/{clientId}/orders")
public class OrderController {
    private final OrderRegisterService orderRegisterService;
    private final OrderRepository orderRepository;
    private final OrderAssembler orderAssembler;

    @GetMapping
    public ResponseEntity<List<OrderRepresentationModel>> findAll(@PathVariable UUID clientId) {
        return ResponseEntity.ok(orderAssembler.toCollectionModel(orderRegisterService.findAll(clientId)));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderRepresentationModel> findById(@PathVariable UUID clientId, @PathVariable UUID orderId) {
        return ResponseEntity.ok(orderAssembler.toModel(orderRegisterService.findById(clientId, orderId)));
    }

    @PostMapping
    public ResponseEntity<OrderRepresentationModel> create(@PathVariable UUID clientId, @RequestBody OrderModelInput orderModelInput) {
        OrderRepresentationModel orderRepresentationModel = orderAssembler.toModel(orderRegisterService.create(clientId, orderModelInput));

        URI location = URI.create(String.format("/clients/%s/orders/%s", clientId, orderRepresentationModel.getId()));

        return ResponseEntity.created(location).body(orderRepresentationModel);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderRepresentationModel> update(@PathVariable UUID clientId, @PathVariable UUID orderId, @RequestBody OrderModelInput orderModelInput) {
        return ResponseEntity.ok(orderAssembler.toModel(orderRegisterService.update(clientId, orderId, orderModelInput)));
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID clientId, @PathVariable UUID orderId) {
        orderRegisterService.delete(clientId, orderId);
    }
}
