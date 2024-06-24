--Creación de la tabla en base de datos de estado
CREATE TABLE status_tbl (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name_status VARCHAR(255) NOT NULL,
    description_status VARCHAR(255) NOT NULL
);
INSERT INTO status_tbl (id,name_status,description_status) values (1, 'activo', 'Estado activo');
INSERT INTO status_tbl (id,name_status,description_status) values (2, 'Inactivo', 'Estado inactiva');
INSERT INTO status_tbl (id,name_status,description_status) values (3, 'Block', 'Estado bloqueado');
INSERT INTO status_tbl (id,name_status,description_status) values (4, 'Cancelado', 'Estado cancelado');

--Creación de la tabla en base de datos del tipo de producto
CREATE TABLE producto_type (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL
);
INSERT INTO producto_type (id,name,description) values (100002, 'Debito', 'Tarjeta de debito');
INSERT INTO producto_type (id,name,description) values (100001, 'Credito', 'Tarjeta de credito');

--Creación de la tabla en de datos de las monedas
CREATE TABLE coin (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL
);
INSERT INTO coin (id,name,description) values (1, 'Dolar', 'Dolar');

--Creación de la tabla en de datos de las tarjetas
CREATE TABLE bank_card (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    card_id VARCHAR(16),
    cardholder_name VARCHAR(255),
    expiration VARCHAR(255),
    status_fk INT,
    product_type_fk INT,
    balance DECIMAL(10, 2),
    FOREIGN KEY (status_fk) REFERENCES status_tbl(id),
    FOREIGN KEY (product_type_fk) REFERENCES producto_type(id)
);

--Creación de la tabla en de datos de rompimiento entre la tarjeta y la moneda
CREATE TABLE card_coin (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    bank_card_fk VARCHAR(255) NOT NULL,
    coin_fk VARCHAR(255) NOT NULL,
    FOREIGN KEY (bank_card_fk) REFERENCES bank_card(id),
    FOREIGN KEY (coin_fk) REFERENCES coin(id)
);

--Creación de la tabla en de datos de la compra
CREATE TABLE transaction (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    transaction_id VARCHAR(255),
    statud_id INT,
    card_id INT,
    sale_value DECIMAL,
    creation_date DATE,
    FOREIGN KEY (statud_id) REFERENCES status_tbl(id),
    FOREIGN KEY (card_id) REFERENCES bank_card(id)
);

