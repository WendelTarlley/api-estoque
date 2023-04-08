package br.com.devtarlley.apiestoque.service;

import br.com.devtarlley.apiestoque.dto.ItemMovimentacaoDTO;
import br.com.devtarlley.apiestoque.enumerated.TipoMovimento;
import br.com.devtarlley.apiestoque.model.HistoricoItem;
import br.com.devtarlley.apiestoque.model.Item;
import br.com.devtarlley.apiestoque.repository.HistoricoItemRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HistoricoItemService {

    private final HistoricoItemRepository historicoItemRepository;

    public HistoricoItemService(HistoricoItemRepository historicoItemRepository) {
        this.historicoItemRepository = historicoItemRepository;
    }

    public void salvarHistoricoEntrada(ItemMovimentacaoDTO itemMovimentacaoDTO){
            HistoricoItem historicoItem = new HistoricoItem(
                    new Item(itemMovimentacaoDTO), itemMovimentacaoDTO.getQuantidade(),
                    TipoMovimento.ENTRADA,new Date()
            );


        historicoItemRepository.save(historicoItem);
    }
    public void salvarHistoricoSaida(ItemMovimentacaoDTO itemMovimentacaoDTO){
            HistoricoItem historicoItem = new HistoricoItem(
                    new Item(itemMovimentacaoDTO), itemMovimentacaoDTO.getQuantidade(),
                    TipoMovimento.SAIDA,new Date()
            );


        historicoItemRepository.save(historicoItem);
    }
}
