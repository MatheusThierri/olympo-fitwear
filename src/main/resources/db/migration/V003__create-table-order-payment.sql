CREATE TABLE tb_order (
    id CHAR(36) PRIMARY KEY,
    order_date TIMESTAMP NOT NULL,
    order_status VARCHAR(15) NOT NULL,
    client_id CHAR(36) NOT NULL,
    FOREIGN KEY (client_id) REFERENCES client (id)
);

CREATE TABLE payment (
    id CHAR(36) PRIMARY KEY,
    payment_date TIMESTAMP NOT NULL,
    FOREIGN KEY (id) REFERENCES tb_order (id)
);

CREATE TABLE order_item (
    order_id CHAR(36) NOT NULL,
    product_id CHAR(36) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES tb_order (id),
    FOREIGN KEY (product_id) REFERENCES product (id)
);
