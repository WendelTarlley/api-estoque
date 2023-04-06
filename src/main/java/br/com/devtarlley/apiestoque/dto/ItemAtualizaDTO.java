package br.com.devtarlley.apiestoque.dto;

import br.com.devtarlley.apiestoque.model.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ItemAtualizaDTO {

    @NotNull
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    private BigDecimal preco;


    public ItemAtualizaDTO(Item item) {
        this.nome = item.getNome();
        this.descricao = item.getDescricao();
    }
}
