package com.olympofitwear.olympo.olympo_api.domain.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderItem {
    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    private Integer quantity;
    private BigDecimal price;

    public OrderItem(Order order, Product product, Integer quantity, BigDecimal price) {
        this.id.setOrder(order);
        this.id.setProduct(product);
        this.quantity = quantity;
        this.price = price;
    }

    public Order getOrder() {
        return id.getOrder();
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }
}
