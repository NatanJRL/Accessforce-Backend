package br.com.fiap.model.endereco;

import jakarta.validation.constraints.NotBlank;

public record DadosInsercaoEnderecoDTO(

        String cep,

        String rua,

        String bairro,

        String cidade,

        @NotBlank
        String estado,

        @NotBlank
        String pais
){}
