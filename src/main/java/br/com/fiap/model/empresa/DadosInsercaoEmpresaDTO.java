package br.com.fiap.model.empresa;

import jakarta.validation.constraints.NotBlank;

public record DadosInsercaoEmpresaDTO(
        @NotBlank
        String nome,
        @NotBlank
        TamanhoEmpresa tamanhoEmpresa
)
{
}
