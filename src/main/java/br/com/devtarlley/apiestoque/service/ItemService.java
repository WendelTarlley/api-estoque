package br.com.devtarlley.apiestoque.service;

import br.com.devtarlley.apiestoque.dto.ItemDTO;
import br.com.devtarlley.apiestoque.model.Item;
import br.com.devtarlley.apiestoque.model.ItemPreco;
import br.com.devtarlley.apiestoque.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemPrecoService itemPrecoService;

    public ItemService(ItemRepository itemRepository, ItemPrecoService itemPrecoService) {
        this.itemRepository = itemRepository;
        this.itemPrecoService = itemPrecoService;
    }

    public ItemDTO cadastrarItem(ItemDTO itemDTO){
        Item item = new Item(itemDTO);
        ItemPreco itemPreco = new ItemPreco();
        itemRepository.save(item);

        itemPreco.setPreco(itemDTO.getPreco());
        itemPreco.setItem(item);

        itemPrecoService.salvarItemPreco(itemPreco);

        return itemDTO;
    }

    public ItemDTO buscarItemPorId(Long id) {

        Optional<Item> item = itemRepository.findById(id);

        if(item.isPresent()){
           ItemDTO itemDTO = new ItemDTO(item.get());
           itemDTO.setPreco(itemPrecoService.buscarUltimoPreco(item.get().getId()).getPreco());
            return itemDTO;
        }
       return null;
    }

}
