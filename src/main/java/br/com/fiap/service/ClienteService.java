package br.com.fiap.service;

import br.com.fiap.ClienteResource;
import br.com.fiap.model.cliente.Cliente;
import br.com.fiap.model.cliente.ListagemClienteDTO;
import br.com.fiap.repository.ClienteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ClienteService {

    public ClienteRepository repository;

    public ClienteService(){
        this.repository = new ClienteRepository();
    }


    public List<ListagemClienteDTO> listarTodosClientes(){
        List<Cliente> lista = repository.listarTodos();
        Stream<ListagemClienteDTO> listaDTO =
                lista.stream().map(ListagemClienteDTO::fromCliente);

        return listaDTO.toList();
    }

}
