package com.olympofitwear.olympo.olympo_api.domain.service;

import com.olympofitwear.olympo.olympo_api.api.model.input.OrderModelInput;
import com.olympofitwear.olympo.olympo_api.api.assembler.OrderAssembler;
import com.olympofitwear.olympo.olympo_api.domain.model.Client;
import com.olympofitwear.olympo.olympo_api.domain.model.Order;
import com.olympofitwear.olympo.olympo_api.domain.model.OrderStatus;
import com.olympofitwear.olympo.olympo_api.domain.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class OrderRegisterService {
    private final OrderRepository orderRepository;
    private final OrderAssembler orderAssembler;
    private final ClientRegisterService clientRegisterService;

    public List<Order> findAll(UUID clientId) {
        return orderRepository.findByClientId(clientId);
    }

    public Order findById(UUID clientId, UUID orderId) {
        Order order = orderRepository.findById(orderId).get();
        if (!order.getClient().getId().equals(clientId)) {
            System.out.println("Order not found for this client");
        }

        return order;
    }

    @Transactional
    public Order update(UUID clientId, UUID orderId, OrderModelInput orderModelInput) {
        Order order = findById(clientId, orderId);
        orderAssembler.toExistingOrder(orderModelInput, order);

        return orderRepository.saveAndFlush(order);
    }

    @Transactional
    public Order create(UUID clientId, OrderModelInput orderModelInput) {
        Order order = orderAssembler.toEntity(orderModelInput);
        Client client = clientRegisterService.findById(clientId);

        order.setOrderDate(OffsetDateTime.now());
        order.setOrderStatus(OrderStatus.WAITING_PAYMENT);
        order.setClient(client);

        return orderRepository.saveAndFlush(order);
    }

    @Transactional
    public void delete(UUID clientId, UUID orderId) {
        Order order = orderRepository.findById(orderId).get();
        if (!order.getClient().getId().equals(clientId)) {
            System.out.println("Order not found for this client");
        }

        orderRepository.deleteById(orderId);
    }
}
