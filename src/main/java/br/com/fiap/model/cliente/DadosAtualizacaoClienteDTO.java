package br.com.fiap.model.cliente;

import br.com.fiap.model.empresa.DadosInsercaoEmpresaDTO;
import br.com.fiap.model.endereco.DadosInsercaoEnderecoDTO;
import br.com.fiap.model.telefone.DadosInsercaoTelefone;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoClienteDTO(
        @NotNull
        Long id,

        String email,

        String senha,

        String nomeCompleto,


        String dataNascimento,

        DadosInsercaoEnderecoDTO endereco,

        String funcao,


        DadosInsercaoEmpresaDTO empresa,


        DadosInsercaoTelefone telefone
) {
}
