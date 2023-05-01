package br.com.devtarlley.apiestoque.service;

import br.com.devtarlley.apiestoque.model.Item;
import br.com.devtarlley.apiestoque.model.ItemPreco;

import java.math.BigDecimal;

public interface ItemPrecoService {
    void salvarItemPreco(Item item, BigDecimal preco);

    ItemPreco buscarUltimoPreco(Long id);
}
