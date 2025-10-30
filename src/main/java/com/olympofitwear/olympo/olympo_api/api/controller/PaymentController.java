package com.olympofitwear.olympo.olympo_api.api.controller;

import com.olympofitwear.olympo.olympo_api.domain.model.Order;
import com.olympofitwear.olympo.olympo_api.domain.model.Payment;
import com.olympofitwear.olympo.olympo_api.domain.service.OrderRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/orders/{id}/payment")
public class PaymentController {
    private final OrderRegisterService orderRegisterService;

    @GetMapping
    public Payment findById(@PathVariable UUID id) {
        Order order = orderRegisterService.findById(id);
        return order.getPayment();
    }
}
