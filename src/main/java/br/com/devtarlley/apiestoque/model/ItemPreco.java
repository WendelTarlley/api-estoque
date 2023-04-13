package br.com.devtarlley.apiestoque.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ItemPreco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private BigDecimal preco;

    private Date dataCadastro;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;


    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
