package br.com.devtarlley.apiestoque.dto;

import br.com.devtarlley.apiestoque.model.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ItemDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @NotNull
    private BigDecimal preco;


    public ItemDTO(Item item) {
        this.nome = item.getNome();
        this.descricao = item.getDescricao();
    }
}
