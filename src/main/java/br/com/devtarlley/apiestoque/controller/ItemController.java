package br.com.devtarlley.apiestoque.controller;

import br.com.devtarlley.apiestoque.dto.ItemAtualizaDTO;
import br.com.devtarlley.apiestoque.dto.ItemDTO;
import br.com.devtarlley.apiestoque.dto.ItemMovimentacaoDTO;
import br.com.devtarlley.apiestoque.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<ItemDTO> cadastrarItem(@Valid @RequestBody ItemDTO item){
        return ResponseEntity.ok().body(itemService.cadastrarItem(item));
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> buscarTodosItens(){
        return ResponseEntity.ok().body(itemService.buscarTodosItens());
    }
    @GetMapping("/por-id/{id}")
    public ResponseEntity<ItemDTO> buscarItemPorId(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(itemService.buscarItemPorId(id));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<ItemAtualizaDTO> atualizarItem(@Valid @RequestBody ItemAtualizaDTO itemAtualizaDTO){
        itemService.atualizarItem(itemAtualizaDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/entrada-item")
    public ResponseEntity<?> entradaItem(@Valid @RequestBody ItemMovimentacaoDTO itemMovimentacaoDTO){
        itemService.entradaItem(itemMovimentacaoDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/saida-item")
    public ResponseEntity<?> saidaItem(@Valid @RequestBody ItemMovimentacaoDTO itemMovimentacaoDTO){
        itemService.saidaItem(itemMovimentacaoDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/multipla-entrada-item")
    public ResponseEntity<?> entradaVariosItens(@Valid @RequestBody List<ItemMovimentacaoDTO> movimentacaoDTOList){
        itemService.entradaVariosItens(movimentacaoDTOList);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/multipla-saida-item")
    public ResponseEntity<?> saidaVariosItens(@Valid @RequestBody List<ItemMovimentacaoDTO> movimentacaoDTOList){
        itemService.saidaVariosItens(movimentacaoDTOList);
        return ResponseEntity.ok().build();
    }
}


