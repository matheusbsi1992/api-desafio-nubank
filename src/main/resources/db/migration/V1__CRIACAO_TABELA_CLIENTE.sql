create table cliente(
         id_cliente SERIAL,
         nome_cliente VARCHAR,
         data_nascimento_cliente timestamp,
         CONSTRAINT cliente_pk PRIMARY KEY (id_cliente)
);