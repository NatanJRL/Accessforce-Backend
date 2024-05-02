package br.com.fiap.model.cliente;

import br.com.fiap.model.endereco.DadosInsercaoEnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosInsercaoClienteDTO(
        @NotBlank
        String email,
        @NotBlank
        String senha,
        @NotBlank
        String nomeCompleto,

        String dataNascimento,

        @Valid
        DadosInsercaoEnderecoDTO endereco,

        String funcao,

        boolean ativo
){}