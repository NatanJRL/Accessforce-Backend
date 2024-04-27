package br.com.fiap.model.cliente;

import br.com.fiap.model.Endereco;

import java.time.LocalDate;

public record DadosInsercaoClienteDTO(
        String email,
        String senha,
        String nomeCompleto,
        String dataNascimento,
        Endereco endereco,
        String funcao,
        boolean ativo
){}
