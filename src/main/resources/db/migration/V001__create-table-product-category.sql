CREATE TABLE category (
    id CHAR(36) PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    description VARCHAR(200) NOT NULL
);

CREATE TABLE product (
    id CHAR(36) PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    description VARCHAR(200) NOT NULL,
    price DECIMAL(10, 2) NOT NULL CHECK (price > 0),
    quantity INTEGER NOT NULL,
    category_id CHAR(36) NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category (id)
);
