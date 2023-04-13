package br.com.devtarlley.apiestoque.dto;

import br.com.devtarlley.apiestoque.model.Item;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ItemMovimentacaoDTO {

    @NotNull
    private Long id;

    @NotBlank
    private String nome;

    @Min(value = 1,message = "Quantidade deve ser maior que 0")
    private Integer quantidade;


    public ItemMovimentacaoDTO(Item item) {
        this.nome = item.getNome();
    }
}
