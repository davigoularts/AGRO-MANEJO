CREATE TABLE lotes (
    id        BIGSERIAL PRIMARY KEY,
    hectares  NUMERIC(10, 2) NOT NULL CHECK (hectares > 0),
    endereco  VARCHAR(255)   NOT NULL,
    planta    VARCHAR(100)   NOT NULL
);
