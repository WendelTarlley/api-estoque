package br.com.devtarlley.apiestoque.service.impl;

import br.com.devtarlley.apiestoque.dto.ItemAtualizaDTO;
import br.com.devtarlley.apiestoque.dto.ItemDTO;
import br.com.devtarlley.apiestoque.dto.ItemMovimentacaoDTO;
import br.com.devtarlley.apiestoque.model.Item;
import br.com.devtarlley.apiestoque.repository.ItemRepository;
import br.com.devtarlley.apiestoque.service.HistoricoItemService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ItemServiceImplTest {

    @InjectMocks
    private ItemServiceImpl itemService;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ItemPrecoServiceImpl itemPrecoService;


    @Captor
    ArgumentCaptor<Item> itemCaptor;
    @Mock
    private HistoricoItemServiceImpl historicoItemService;



    @Test
    void deveRetornarItemSalvo(){
        ItemDTO itemDTO = new ItemDTO("item1", "descricao", new BigDecimal("15.05"), 0);
        ItemDTO itemSalvo = itemService.cadastrarItem(itemDTO);
        assertEquals(itemDTO,itemSalvo);
    }

    @Test
    void deveChamarTodosMetodos(){

        ItemAtualizaDTO itemAtualizaDTO = new ItemAtualizaDTO(1L, "item1", "descricao", new BigDecimal("10.5"));
        Item item = new Item(itemAtualizaDTO);

        Mockito.when(itemRepository.findById(itemAtualizaDTO.getId())).thenReturn(Optional.of(item));
        itemService.atualizarItem(itemAtualizaDTO);

        Mockito.verify(itemRepository).findById(itemAtualizaDTO.getId());
        Mockito.verify(itemPrecoService).salvarItemPreco(item,itemAtualizaDTO.getPreco());
        Mockito.verify(itemRepository).save(item);

    }

    @Test
    @DisplayName("Quantidade entrada Negativa.")
    void deveDispararExcecaoEntradaQuantidadeNegativa(){

        ItemMovimentacaoDTO itemMovimentacaoDTO = new ItemMovimentacaoDTO(1L, "item1", -1);

        assertThrows(ResponseStatusException.class,
                () -> itemService.entradaItem(itemMovimentacaoDTO));
    }
    @Test
    @DisplayName("Quantidade entrada zero.")
    void deveDispararExcecaoEntradaQuantidadeZero(){

        ItemMovimentacaoDTO itemMovimentacaoDTO = new ItemMovimentacaoDTO(1L, "item1", 0);

        assertThrows(ResponseStatusException.class,
                () -> itemService.entradaItem(itemMovimentacaoDTO));
    }
    @Test
    @DisplayName("Quantidade saida zero.")
    void deveDispararExcecaoSaidaMaiorQueQuantidadeExistente(){

        ItemMovimentacaoDTO itemMovimentacaoDTO = new ItemMovimentacaoDTO(1L, "item1", 10);
        ItemMovimentacaoDTO itemMovimentoSalvo = new ItemMovimentacaoDTO(1L, "item1", 5);

        Mockito.when(itemRepository.findById(itemMovimentacaoDTO.getId())).thenReturn(Optional.of(new Item(itemMovimentoSalvo)));
        assertThrows(ResponseStatusException.class,
                () -> itemService.saidaItem(itemMovimentacaoDTO));
    }
    @Test
    @DisplayName("Quantidade saida negativa.")
    void deveDispararExcecaoSaidaNegativa(){

        ItemMovimentacaoDTO itemMovimentacaoDTO = new ItemMovimentacaoDTO(1L, "item1", -5);
        assertThrows(ResponseStatusException.class,
                () -> itemService.saidaItem(itemMovimentacaoDTO));
    }

    @Test
    @DisplayName("Quantidade saida igual a zero.")
    void deveDispararExcecaoSaidaIgualAZero(){

        ItemMovimentacaoDTO itemMovimentacaoDTO = new ItemMovimentacaoDTO(1L, "item1", 0);
        assertThrows(ResponseStatusException.class,
                () -> itemService.saidaItem(itemMovimentacaoDTO));
    }

    @Test
    @DisplayName("Item não encontrado")
    void deveDispararExcecaoItemNaoEncontrado(){

        ItemMovimentacaoDTO itemMovimentacaoDTO = new ItemMovimentacaoDTO(1L, "item1", 10);
        Mockito.when(itemRepository.findById(itemMovimentacaoDTO.getId())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class,
                () -> itemService.saidaItem(itemMovimentacaoDTO));
    }

}