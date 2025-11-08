CREATE TABLE tb_order (
    id BINARY(16) NOT NULL PRIMARY KEY,
    order_date TIMESTAMP NOT NULL,
    order_status VARCHAR(15) NOT NULL,
    client_id BINARY(16) NOT NULL,
    FOREIGN KEY (client_id) REFERENCES client (id)
);

CREATE TABLE payment (
    id BINARY(16) NOT NULL PRIMARY KEY,
    payment_date TIMESTAMP NOT NULL,
    FOREIGN KEY (id) REFERENCES tb_order (id)
);

CREATE TABLE order_item (
    order_id BINARY(16) NOT NULL,
    product_id BINARY(16) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES tb_order (id),
    FOREIGN KEY (product_id) REFERENCES product (id)
);
