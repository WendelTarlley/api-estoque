package br.com.devtarlley.apiestoque.repository;

import br.com.devtarlley.apiestoque.model.ItemPreco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemPrecoRepository extends JpaRepository<ItemPreco, Long> {
    Optional<ItemPreco> findFirstByItem_IdOrderByDataCadastroDesc(Long id);
}