package br.com.fiap.model.cliente;

import br.com.fiap.model.empresa.DadosInsercaoEmpresaDTO;
import br.com.fiap.model.endereco.DadosInsercaoEnderecoDTO;
import br.com.fiap.model.telefone.DadosInsercaoTelefone;
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

        @NotNull
        String dataNascimento,

        @Valid
        DadosInsercaoEnderecoDTO endereco,

        String funcao,

        @Valid
        DadosInsercaoEmpresaDTO empresa,

        @Valid
        DadosInsercaoTelefone telefone,

        boolean ativo
){}