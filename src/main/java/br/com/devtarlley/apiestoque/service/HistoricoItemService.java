package br.com.devtarlley.apiestoque.service;

import br.com.devtarlley.apiestoque.dto.ItemMovimentacaoDTO;

public interface HistoricoItemService {
    void salvarHistoricoEntrada(ItemMovimentacaoDTO itemMovimentacaoDTO);

    void salvarHistoricoSaida(ItemMovimentacaoDTO itemMovimentacaoDTO);
}
