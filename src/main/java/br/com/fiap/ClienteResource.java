package br.com.fiap;


import br.com.fiap.model.cliente.Cliente;
import br.com.fiap.model.cliente.DadosInsercaoClienteDTO;
import br.com.fiap.model.cliente.ListagemClienteDTO;
import br.com.fiap.repository.ClienteRepository;
import br.com.fiap.repository.EnderecoRepository;
import br.com.fiap.service.ClienteService;
import jakarta.json.bind.annotation.JsonbPropertyOrder;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/clientes")
public class ClienteResource {

    private ClienteRepository clienteRepository = new ClienteRepository();
    private EnderecoRepository enderecoRepository = new EnderecoRepository();

    private ClienteService clienteService = new ClienteService();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ListagemClienteDTO> listarClientes(){
        return clienteService.listarTodosClientes();
    }
    @GET
    @Path(("{id}"))
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente listarClientePorId(@PathParam("id") Long id){
        return clienteRepository.getClientById(id);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirCliente(@Valid DadosInsercaoClienteDTO insercaoClienteDTO){
        Cliente cliente = new Cliente(insercaoClienteDTO);

        long id = clienteRepository.inserirCliente(cliente);
        enderecoRepository.inserirEndereco(cliente.getEndereco(), id);
        return Response.status(Response.Status.CREATED).build();
    }




}


