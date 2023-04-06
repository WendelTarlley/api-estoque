package br.com.devtarlley.apiestoque.model;

import br.com.devtarlley.apiestoque.enumerated.TipoMovimento;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class HistoricoItem {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private Integer quantidadeMovimentada;

    @Enumerated(EnumType.STRING)
    private TipoMovimento tipoMovimento;
//TODO ajustar informações de usuário para inserir nas alterações
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    private Date updatedAt;


    public HistoricoItem(Item item, Integer quantidadeMovimentada, TipoMovimento tipoMovimento, Date updatedAt) {
        this.item = item;
        this.quantidadeMovimentada = quantidadeMovimentada;
        this.tipoMovimento = tipoMovimento;
        this.updatedAt = updatedAt;
    }
}
