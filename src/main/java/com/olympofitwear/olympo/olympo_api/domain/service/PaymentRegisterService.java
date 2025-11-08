package com.olympofitwear.olympo.olympo_api.domain.service;

import com.olympofitwear.olympo.olympo_api.domain.model.Order;
import com.olympofitwear.olympo.olympo_api.domain.model.Payment;
import com.olympofitwear.olympo.olympo_api.domain.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@AllArgsConstructor
@Service
public class PaymentRegisterService {
    private final OrderRegisterService orderRegisterService;
    private final OrderRepository orderRepository;

    public Order create(UUID orderId) {
        Payment payment = new Payment();
        payment.setPaymentDate(OffsetDateTime.now());
        Order order = orderRegisterService.findById(orderId);
        payment.setOrder(order);
        order.setPayment(payment);
        return orderRepository.saveAndFlush(order);
    }

    public void delete(UUID id) {
        Order order = orderRegisterService.findById(id);
        order.setPayment(null);
        orderRepository.saveAndFlush(order);
    }
}
