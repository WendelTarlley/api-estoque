package br.com.devtarlley.apiestoque.model;

import br.com.devtarlley.apiestoque.dto.ItemDTO;
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


    public Item(ItemDTO itemDTO) {
        this.nome = itemDTO.getNome();
        this.descricao = itemDTO.getDescricao();
    }
}
