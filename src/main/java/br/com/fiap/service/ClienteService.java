package br.com.fiap.service;

import br.com.fiap.model.cliente.Cliente;
import br.com.fiap.model.cliente.DadosAtualizacaoClienteDTO;
import br.com.fiap.model.cliente.DadosInsercaoClienteDTO;
import br.com.fiap.model.cliente.ListagemClienteDTO;
import br.com.fiap.model.empresa.Empresa;
import br.com.fiap.model.endereco.Endereco;
import br.com.fiap.model.telefone.Telefone;
import br.com.fiap.repository.ClienteRepository;
import br.com.fiap.repository.EmpresaRepository;
import br.com.fiap.repository.EnderecoRepository;
import br.com.fiap.repository.TelefoneRepository;

import java.time.LocalDate;
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
    public void deletar(Long id){
        clienteRepository.deletarCliente(id);
    }

    public void atualizar(DadosAtualizacaoClienteDTO dados) {
        Cliente clienteParaAtualizar = clienteRepository.getClientById(dados.id());

        if (dados.email() != null) clienteParaAtualizar.setEmail(dados.email());

        if (dados.senha() != null) clienteParaAtualizar.setSenha(dados.senha());

        if (dados.nomeCompleto() != null) clienteParaAtualizar.setNomeCompleto(dados.nomeCompleto());


        if (dados.dataNascimento() != null) clienteParaAtualizar.setDataNascimento(LocalDate.parse(dados.dataNascimento()));

        if (dados.endereco() != null) clienteParaAtualizar.setEndereco(new Endereco(dados.endereco()));

        if (dados.funcao() != null) clienteParaAtualizar.setFuncao(dados.funcao());

        if (dados.empresa() != null) clienteParaAtualizar.setEmpresa(new Empresa(dados.empresa()));

        if (dados.telefone() != null) clienteParaAtualizar.setTelefones(List.of(new Telefone(dados.telefone())));

        clienteRepository.atualizarCliente(clienteParaAtualizar);
        }
}
