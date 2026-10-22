CREATE TABLE compradores (
    id_comprador BIGSERIAL PRIMARY KEY,
    cnpj         VARCHAR(14)    NOT NULL UNIQUE,
    endereco     VARCHAR(255)   NOT NULL,
    planta_preco VARCHAR(100)   NOT NULL,
    preco        NUMERIC(12, 2) NOT NULL CHECK (preco > 0)
);
