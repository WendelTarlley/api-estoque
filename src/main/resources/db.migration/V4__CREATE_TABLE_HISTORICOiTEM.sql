CREATE TABLE historico_item (
  id UUID NOT NULL,
   item_id BIGINT,
   quantidade_movimentada INTEGER,
   tipo_movimento VARCHAR(255),
   updated_at TIMESTAMP WITHOUT TIME ZONE,
   CONSTRAINT pk_historicoitem PRIMARY KEY (id)
);

ALTER TABLE historico_item ADD CONSTRAINT FK_HISTORICOITEM_ON_ITEM FOREIGN KEY (item_id) REFERENCES itens (id);