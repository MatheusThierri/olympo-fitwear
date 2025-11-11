package com.olympofitwear.olympo.olympo_api.domain.repository;

import com.olympofitwear.olympo.olympo_api.domain.model.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByClientId(UUID id);
}
