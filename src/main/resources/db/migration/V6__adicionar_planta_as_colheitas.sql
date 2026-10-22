ALTER TABLE colheitas
    ADD COLUMN planta VARCHAR(255);

UPDATE colheitas c
SET planta = l.planta
FROM lotes l
WHERE c.lote_id = l.id;

ALTER TABLE colheitas
    ALTER COLUMN planta SET NOT NULL;
