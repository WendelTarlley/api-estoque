package br.com.devtarlley.apiestoque.service;

import br.com.devtarlley.apiestoque.model.ItemPreco;
import br.com.devtarlley.apiestoque.repository.ItemPrecoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ItemPrecoService {

    private final ItemPrecoRepository itemPrecoRepository;

    public ItemPrecoService(ItemPrecoRepository itemPrecoRepository) {
        this.itemPrecoRepository = itemPrecoRepository;
    }


    public void salvarItemPreco(ItemPreco itemPreco){

        itemPreco.setDataCadastro(new Date());
        //TODO  setar o usuário a partir do TOKEN  itemPreco.setUser();
        itemPrecoRepository.save(itemPreco);
    }

    public ItemPreco  buscarUltimoPreco(Long id) {
      return itemPrecoRepository.findFirstByItem_IdOrderByDataCadastroDesc(id).orElseThrow(RuntimeException::new);
    }
}
