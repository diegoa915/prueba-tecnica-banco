CREATE DATABASE banco;
USE banco;

CREATE TABLE persona (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    genero VARCHAR(10),
    edad INT,
    identificacion VARCHAR(20) UNIQUE,
    telefono VARCHAR(20)
);

CREATE TABLE cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    persona_id BIGINT,
    estado BOOLEAN,
    FOREIGN KEY (persona_id) REFERENCES persona(id)
);

CREATE TABLE cuenta (
    numero BIGINT PRIMARY KEY,
    tipo VARCHAR(20),
    saldo_inicial DECIMAL(10,2),
    estado BOOLEAN,
    cliente_id BIGINT,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

CREATE TABLE movimiento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE,
    tipo VARCHAR(20),
    valor DECIMAL(10,2),
    saldo DECIMAL(10,2),
    cuenta_id BIGINT,
    FOREIGN KEY (cuenta_id) REFERENCES cuenta(numero)
);
