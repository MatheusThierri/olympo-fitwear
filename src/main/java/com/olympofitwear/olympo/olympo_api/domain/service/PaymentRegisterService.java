package com.olympofitwear.olympo.olympo_api.domain.service;

import com.olympofitwear.olympo.olympo_api.domain.model.Order;
import com.olympofitwear.olympo.olympo_api.domain.model.Payment;
import com.olympofitwear.olympo.olympo_api.domain.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

@AllArgsConstructor
@Service
public class PaymentRegisterService {
    private final OrderRegisterService orderRegisterService;
    private final OrderRepository orderRepository;

    @Transactional
    public Order create(UUID clientId, UUID orderId) {
        Order order = orderRegisterService.findById(clientId, orderId);
        if (!order.getClient().getId().equals(clientId)) {
            System.out.println("Order not found for this client");
        }
        Payment payment = new Payment();
        payment.setPaymentDate(OffsetDateTime.now());
        payment.setOrder(order);
        order.setPayment(payment);

        return orderRepository.saveAndFlush(order);
    }

    @Transactional
    public void delete(UUID clientId, UUID orderId) {
        Order order = orderRegisterService.findById(clientId, orderId);
        if (!order.getClient().getId().equals(clientId)) {
            System.out.println("Order not found for this client");
        }
        order.setPayment(null);

        orderRepository.saveAndFlush(order);
    }
}
