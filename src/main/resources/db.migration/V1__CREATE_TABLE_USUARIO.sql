CREATE TABLE usuario (
  id UUID NOT NULL,
   nome VARCHAR(255),
   email VARCHAR(255),
   senha VARCHAR(255),
   cpf VARCHAR(255),
   CONSTRAINT pk_usuario PRIMARY KEY (id)
);