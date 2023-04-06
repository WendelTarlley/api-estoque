package br.com.devtarlley.apiestoque.model;

import br.com.devtarlley.apiestoque.dto.ItemAtualizaDTO;
import br.com.devtarlley.apiestoque.dto.ItemDTO;
import br.com.devtarlley.apiestoque.dto.ItemMovimentacaoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="itens")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nome;

    private String descricao;

    @Column(columnDefinition = "integer default 0",nullable = false)
    private Integer quantidade = 0;

    public Item(ItemDTO itemDTO) {
        this.nome = itemDTO.getNome();
        this.descricao = itemDTO.getDescricao();
    }

    public Item(ItemAtualizaDTO itemAtualizaDTO) {
        this.id = itemAtualizaDTO.getId();
        this.nome = itemAtualizaDTO.getNome();
        this.descricao = itemAtualizaDTO.getDescricao();
    }
    public Item(ItemMovimentacaoDTO movimentacaoDTO) {
        this.id = movimentacaoDTO.getId();
        this.nome = movimentacaoDTO.getNome();
        this.quantidade = movimentacaoDTO.getQuantidade();
    }
}
