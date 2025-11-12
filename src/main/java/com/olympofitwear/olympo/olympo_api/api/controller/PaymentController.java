package com.olympofitwear.olympo.olympo_api.api.controller;

import com.olympofitwear.olympo.olympo_api.api.assembler.OrderAssembler;
import com.olympofitwear.olympo.olympo_api.api.model.output.PaymentRepresentationModel;
import com.olympofitwear.olympo.olympo_api.api.assembler.PaymentAssembler;
import com.olympofitwear.olympo.olympo_api.domain.model.Order;
import com.olympofitwear.olympo.olympo_api.domain.service.OrderRegisterService;
import com.olympofitwear.olympo.olympo_api.domain.service.PaymentRegisterService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/clients/{clientId}/orders/{orderId}/payment")
public class PaymentController {
    private final OrderRegisterService orderRegisterService;
    private final PaymentAssembler paymentAssembler;
    private final PaymentRegisterService paymentRegisterService;

    @GetMapping
    public ResponseEntity<PaymentRepresentationModel> findById(@PathVariable UUID clientId, @PathVariable UUID orderId) {
        Order order = orderRegisterService.findById(clientId, orderId);
        return ResponseEntity.ok(paymentAssembler.toModel(order.getPayment()));
    }

    @PostMapping
    public ResponseEntity<PaymentRepresentationModel> create(@PathVariable UUID clientId, @PathVariable UUID orderId) {
        Order order = paymentRegisterService.create(clientId, orderId);

        URI location = URI.create(String.format("/clients/%s/orders/%s/payment", clientId, orderId));

        return ResponseEntity.created(location).body(paymentAssembler.toModel(order.getPayment()));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID clientId, @PathVariable UUID orderId) {
        paymentRegisterService.delete(clientId, orderId);
    }
}
