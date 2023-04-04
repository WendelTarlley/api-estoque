package br.com.devtarlley.apiestoque.controller;

import br.com.devtarlley.apiestoque.dto.ItemDTO;
import br.com.devtarlley.apiestoque.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/itens")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarItem(@Valid @RequestBody ItemDTO item){
        return ResponseEntity.ok().body(itemService.cadastrarItem(item));
    }

    @GetMapping("por-id/{id}")
    public ResponseEntity<?> buscarItemPorId(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(itemService.buscarItemPorId(id));
    }
}
