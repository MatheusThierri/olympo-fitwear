CREATE TABLE client (
    id CHAR(36) PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    email VARCHAR(200) NOT NULL,
    password VARCHAR(255) NOT NULL,
    cpf CHAR(11) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL
);

CREATE TABLE address (
    id CHAR(36) PRIMARY KEY,
    street VARCHAR(60) NOT NULL,
    neighborhood VARCHAR(40) NOT NULL,
    number VARCHAR(10) NOT NULL,
    cep VARCHAR(20) NOT NULL,
    client_id CHAR(36) NOT NULL,
    FOREIGN KEY (client_id) REFERENCES client (id)
);