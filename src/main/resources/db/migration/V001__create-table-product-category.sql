CREATE TABLE category (
    id BINARY(16) NOT NULL PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    description VARCHAR(200) NOT NULL
);

CREATE TABLE product (
    id BINARY(16) NOT NULL PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    description VARCHAR(200) NOT NULL,
    price DECIMAL(10, 2) NOT NULL CHECK (price > 0),
    quantity INTEGER NOT NULL,
    category_id BINARY(16) NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category (id)
);
