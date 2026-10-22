CREATE TABLE vendas (
    id           BIGSERIAL PRIMARY KEY,
    id_comprador BIGINT         NOT NULL REFERENCES compradores (id_comprador),
    planta       VARCHAR(100)   NOT NULL,
    quantidade   NUMERIC(12, 2) NOT NULL CHECK (quantidade > 0),
    preco        NUMERIC(12, 2) NOT NULL CHECK (preco > 0),
    data         DATE           NOT NULL
);
