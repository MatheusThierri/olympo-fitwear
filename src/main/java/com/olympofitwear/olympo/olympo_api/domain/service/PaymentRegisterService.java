package com.olympofitwear.olympo.olympo_api.domain.service;

import com.olympofitwear.olympo.olympo_api.domain.exception.DomainException;
import com.olympofitwear.olympo.olympo_api.domain.model.Client;
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
    private final ClientRegisterService clientRegisterService;

    @Transactional
    public Order create(UUID clientId, UUID orderId) {
        Client client = clientRegisterService.findById(clientId);

        Order order = findValidOrder(client.getId(), orderId);

        Payment payment = new Payment();
        payment.setPaymentDate(OffsetDateTime.now());
        payment.setOrder(order);

        order.setPayment(payment);

        return orderRepository.saveAndFlush(order);
    }

    @Transactional
    public void delete(UUID clientId, UUID orderId) {
        Client client = clientRegisterService.findById(clientId);

        Order order = findValidOrder(client.getId(), orderId);
        order.setPayment(null);

        orderRepository.saveAndFlush(order);
    }

    private Order findValidOrder(UUID clientId, UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new DomainException("Order not found with ID: " + orderId));

        if (!order.getClient().getId().equals(clientId)) {
            throw new DomainException("Order not found for this client with ID: " + clientId);
        }

        return order;
    }
}
