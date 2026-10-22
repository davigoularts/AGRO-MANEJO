CREATE TABLE colheitas (
    id         BIGSERIAL PRIMARY KEY,
    lote_id    BIGINT         NOT NULL REFERENCES lotes (id),
    quantidade NUMERIC(12, 2) NOT NULL CHECK (quantidade > 0)
);
