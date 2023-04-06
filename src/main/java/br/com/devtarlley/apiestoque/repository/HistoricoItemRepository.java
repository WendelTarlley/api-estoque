package br.com.devtarlley.apiestoque.repository;

import br.com.devtarlley.apiestoque.model.HistoricoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HistoricoItemRepository extends JpaRepository<HistoricoItem, UUID> {
}