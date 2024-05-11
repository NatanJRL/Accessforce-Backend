package br.com.fiap.service;

import br.com.fiap.model.cliente.Cliente;
import br.com.fiap.model.cliente.DadosInsercaoClienteDTO;
import br.com.fiap.model.cliente.ListagemClienteDTO;
import br.com.fiap.model.empresa.Empresa;
import br.com.fiap.model.telefone.Telefone;
import br.com.fiap.repository.ClienteRepository;
import br.com.fiap.repository.EmpresaRepository;
import br.com.fiap.repository.EnderecoRepository;
import br.com.fiap.repository.TelefoneRepository;

import java.util.List;
import java.util.stream.Stream;

public class ClienteService {

    public ClienteRepository clienteRepository;
    public EnderecoRepository enderecoRepository;
    public TelefoneRepository telefoneReposirory;
    public EmpresaRepository empresaRepository;
    public ClienteService(){
        this.clienteRepository = new ClienteRepository();
        this.enderecoRepository = new EnderecoRepository();
        this.telefoneReposirory = new TelefoneRepository();
        this.empresaRepository = new EmpresaRepository();
    }

    public List<ListagemClienteDTO> listarTodosClientes(){
        List<Cliente> lista = clienteRepository.listarTodos();
        Stream<ListagemClienteDTO> listaDTO =
                lista.stream().map(ListagemClienteDTO::fromCliente);

        return listaDTO.toList();
    }

    public ListagemClienteDTO recuperarPorID(Long id){
        Cliente cliente = clienteRepository.getClientById(id);
        return ListagemClienteDTO.fromCliente(cliente);
    }

    public void inserir(DadosInsercaoClienteDTO dados){
        Cliente cliente = new Cliente(dados);

        long idCliente = clienteRepository.inserirCliente(cliente);
        Telefone telefone = new Telefone(dados.telefone());
        Empresa empresa = new Empresa(dados.empresa());

        telefoneReposirory.inserirTelefone(telefone, idCliente);
        enderecoRepository.inserirEndereco(cliente.getEndereco(), idCliente);
        empresaRepository.inserirEmpresa(empresa, idCliente);

    }
}