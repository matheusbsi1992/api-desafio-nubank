create table cliente(
         id_cliente SERIAL NOT NULL,
         nome_cliente VARCHAR,
         data_nascimento_cliente DATE,
         CONSTRAINT cliente_pk PRIMARY KEY (id_cliente)
);