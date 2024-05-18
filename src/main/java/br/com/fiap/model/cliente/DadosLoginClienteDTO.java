package br.com.fiap.model.cliente;

import jakarta.validation.constraints.NotBlank;

public record DadosLoginClienteDTO(

        @NotBlank
        String email,
        @NotBlank
        String senha
) {
}
