package br.com.devtarlley.apiestoque.service.impl;

import br.com.devtarlley.apiestoque.model.Item;
import br.com.devtarlley.apiestoque.model.ItemPreco;
import br.com.devtarlley.apiestoque.repository.ItemPrecoRepository;
import br.com.devtarlley.apiestoque.service.ItemPrecoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class ItemPrecoServiceImpl implements ItemPrecoService {

    private final ItemPrecoRepository itemPrecoRepository;

    public ItemPrecoServiceImpl(ItemPrecoRepository itemPrecoRepository) {
        this.itemPrecoRepository = itemPrecoRepository;
    }

    @Override
    public void salvarItemPreco(Item item, BigDecimal preco){
        ItemPreco itemPreco = new ItemPreco();

        itemPreco.setItem(item);
        itemPreco.setPreco(preco);
        itemPreco.setDataCadastro(new Date());
        //TODO  setar o usuário a partir do TOKEN  itemPreco.setUser();
        itemPrecoRepository.save(itemPreco);
    }

    @Override
    public ItemPreco  buscarUltimoPreco(Long id) {
      return itemPrecoRepository.findFirstByItem_IdOrderByDataCadastroDesc(id).orElseThrow(RuntimeException::new);
    }
}
