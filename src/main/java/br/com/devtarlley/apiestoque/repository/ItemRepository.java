package br.com.devtarlley.apiestoque.repository;

import br.com.devtarlley.apiestoque.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByNome(String nome);
}