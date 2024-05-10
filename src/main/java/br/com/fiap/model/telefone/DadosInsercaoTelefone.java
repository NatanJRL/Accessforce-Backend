package br.com.fiap.model.telefone;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosInsercaoTelefone(
        @NotNull
        int ddd,

        @NotBlank
        String numero,

        @NotNull
        TipoTelefone tipoTelefone
) {
}
