package com.olympofitwear.olympo.olympo_api.api.controller;

import com.olympofitwear.olympo.olympo_api.api.model.output.PaymentRepresentationModel;
import com.olympofitwear.olympo.olympo_api.assembler.PaymentAssembler;
import com.olympofitwear.olympo.olympo_api.domain.model.Order;
import com.olympofitwear.olympo.olympo_api.domain.model.Payment;
import com.olympofitwear.olympo.olympo_api.domain.repository.OrderRepository;
import com.olympofitwear.olympo.olympo_api.domain.service.OrderRegisterService;
import com.olympofitwear.olympo.olympo_api.domain.service.PaymentRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/orders/{orderId}/payment")
public class PaymentController {
    private final OrderRegisterService orderRegisterService;
    private final PaymentAssembler paymentAssembler;
    private final PaymentRegisterService paymentRegisterService;

    @GetMapping
    public ResponseEntity<PaymentRepresentationModel> findById(@PathVariable UUID orderId) {
        Order order = orderRegisterService.findById(orderId);
        return ResponseEntity.ok(paymentAssembler.toModel(order.getPayment()));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentRepresentationModel create(@PathVariable UUID orderId) {
        Order order = paymentRegisterService.create(orderId);
        return paymentAssembler.toModel(order.getPayment());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID orderId) {
        paymentRegisterService.delete(orderId);
    }
}
