CREATE TABLE contato (
                id_contato SERIAL NOT NULL,
                id_cliente INTEGER,
                endereco_contato VARCHAR,
                numero_contato VARCHAR,
                CONSTRAINT contato_pk PRIMARY KEY (id_contato)
);

ALTER TABLE contato ADD CONSTRAINT contato_cliente_fk
FOREIGN KEY (id_cliente)
REFERENCES cliente (id_cliente)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;