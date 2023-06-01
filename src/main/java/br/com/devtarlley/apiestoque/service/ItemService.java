package br.com.devtarlley.apiestoque.service;

import br.com.devtarlley.apiestoque.dto.ItemAtualizaDTO;
import br.com.devtarlley.apiestoque.dto.ItemDTO;
import br.com.devtarlley.apiestoque.dto.ItemMovimentacaoDTO;

import java.util.List;

public interface ItemService {
    ItemDTO cadastrarItem(ItemDTO itemDTO);

    List<ItemDTO> buscarTodosItens();

    ItemDTO buscarItemPorId(Long id);

    void atualizarItem(ItemAtualizaDTO atualizaDTO);

    void entradaItem(ItemMovimentacaoDTO itemMovimentacaoDTO);

    void saidaItem(ItemMovimentacaoDTO itemMovimentacaoDTO);

    void entradaVariosItens(List<ItemMovimentacaoDTO> movimentacaoDTOList);

    void saidaVariosItens(List<ItemMovimentacaoDTO> movimentacaoDTOList);
}
