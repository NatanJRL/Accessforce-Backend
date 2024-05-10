package br.com.fiap;


import br.com.fiap.model.cliente.DadosInsercaoClienteDTO;
import br.com.fiap.model.cliente.ListagemClienteDTO;
import br.com.fiap.service.ClienteService;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/clientes")
public class ClienteResource {


    private ClienteService clienteService = new ClienteService();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ListagemClienteDTO> listarClientes(){
        return clienteService.listarTodosClientes();
    }
    @GET
    @Path(("{id}"))
    @Produces(MediaType.APPLICATION_JSON)
    public ListagemClienteDTO listarClientePorId(@PathParam("id") Long id){
        return clienteService.recuperarPorID(id);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirCliente(@Valid DadosInsercaoClienteDTO insercaoClienteDTO){
        clienteService.inserir(insercaoClienteDTO);
        return Response.status(Response.Status.CREATED).build();
    }




}


