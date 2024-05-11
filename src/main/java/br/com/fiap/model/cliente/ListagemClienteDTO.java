package br.com.fiap.model.cliente;

import br.com.fiap.model.empresa.Empresa;
import br.com.fiap.model.endereco.ListagemEnderecoDTO;
import br.com.fiap.model.telefone.Telefone;

import java.time.LocalDate;
import java.util.List;

public record ListagemClienteDTO(

        Long id,
        String nomeCompleto,
        String email,
        String senha,
        LocalDate dataNascimento,
        LocalDate dataDeRegistro,

        ListagemEnderecoDTO endereco,
        String funcao,
        boolean ativo,
        Empresa empresa,
        List<Telefone> telefones
) {

    //construtor recebendo cliente

    public static ListagemClienteDTO fromCliente(Cliente cliente) {
        return new ListagemClienteDTO(
                cliente.getId(),
                cliente.getNomeCompleto(),
                cliente.getEmail(),
                cliente.getSenha(),
                cliente.getDataNascimento(),
                cliente.getDataDeRegistro(),
                new ListagemEnderecoDTO(cliente.getEndereco().getEstado(), cliente.getEndereco().getPais()),
                cliente.getFuncao(),
                cliente.isAtivo(),
                cliente.getEmpresa(),
                cliente.getTelefones()
        );
}
}
