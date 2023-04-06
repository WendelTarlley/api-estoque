package br.com.devtarlley.apiestoque.service;

import br.com.devtarlley.apiestoque.dto.ItemAtualizaDTO;
import br.com.devtarlley.apiestoque.dto.ItemDTO;
import br.com.devtarlley.apiestoque.dto.ItemMovimentacaoDTO;
import br.com.devtarlley.apiestoque.model.Item;
import br.com.devtarlley.apiestoque.repository.ItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemPrecoService itemPrecoService;
    private final HistoricoItemService historicoItemService;

    public ItemService(ItemRepository itemRepository, ItemPrecoService itemPrecoService, HistoricoItemService historicoItemService) {
        this.itemRepository = itemRepository;
        this.itemPrecoService = itemPrecoService;
        this.historicoItemService = historicoItemService;
    }

    public ItemDTO cadastrarItem(ItemDTO itemDTO) {
        Item item = new Item(itemDTO);
        itemRepository.save(item);

        itemPrecoService.salvarItemPreco(item, itemDTO.getPreco());

        return itemDTO;
    }


    public List<ItemDTO> buscarTodosItens() {
        return itemRepository.findAll().stream().map(ItemDTO::new).toList();
    }

    public ItemDTO buscarItemPorId(Long id) {

        Optional<Item> item = itemRepository.findById(id);

        if (item.isPresent()) {
            ItemDTO itemDTO = new ItemDTO(item.get());
            itemDTO.setPreco(itemPrecoService.buscarUltimoPreco(item.get().getId()).getPreco());
            return itemDTO;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Elemento não encontrado para o id: %s", id));
        }
    }

    public void atualizarItem(ItemAtualizaDTO atualizaDTO) {
        Optional<Item> findItem = itemRepository.findById(atualizaDTO.getId());

        findItem.ifPresent(value -> {
            if (atualizaDTO.getPreco() != null) {
                itemPrecoService.salvarItemPreco(findItem.get(), atualizaDTO.getPreco());
            }

            value.setDescricao(atualizaDTO.getDescricao());
            value.setNome(atualizaDTO.getNome());
            itemRepository.save(value);
        });
    }

    public void entradaItem(ItemMovimentacaoDTO itemMovimentacaoDTO) {

        itemRepository.findById(itemMovimentacaoDTO.getId()).
                ifPresentOrElse(item -> {
                            try {
                                item.setQuantidade(item.getQuantidade() + itemMovimentacaoDTO.getQuantidade());
                                itemRepository.save(item);
                                historicoItemService.salvarHistoricoEntrada(itemMovimentacaoDTO);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        },
                        () -> {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Elemento não encontrado para o id: %s", itemMovimentacaoDTO.getId()));
                        }
                );
    }

    public void saidaItem(ItemMovimentacaoDTO itemMovimentacaoDTO) {
        itemRepository.findById(itemMovimentacaoDTO.getId()).
                ifPresentOrElse(item -> {
                                if (itemMovimentacaoDTO.getQuantidade() > item.getQuantidade()) {
                                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Item de id: %s sem quantidade suficiente",item.getId()));
                                }
                            try {
                                item.setQuantidade(item.getQuantidade() - itemMovimentacaoDTO.getQuantidade());
                                itemRepository.save(item);
                                historicoItemService.salvarHistoricoSaida(itemMovimentacaoDTO);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        },
                        () -> {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Elemento não encontrado para o id: %s", itemMovimentacaoDTO.getId()));
                        }
                );
    }

    public void entradaVariosItens(List<ItemMovimentacaoDTO> movimentacaoDTOList){
        movimentacaoDTOList.forEach(this::entradaItem);
    }
    public void saidaVariosItens(List<ItemMovimentacaoDTO> movimentacaoDTOList){
        movimentacaoDTOList.forEach(this::saidaItem);
    }

}

