CREATE TABLE insumos (
    id         BIGSERIAL PRIMARY KEY,
    nome_item  VARCHAR(100)   NOT NULL UNIQUE,
    quantidade NUMERIC(12, 2) NOT NULL CHECK (quantidade >= 0)
);
