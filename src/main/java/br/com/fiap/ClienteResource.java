package br.com.fiap;


import br.com.fiap.model.cliente.Cliente;
import br.com.fiap.model.Endereco;
import br.com.fiap.model.cliente.DadosInsercaoClienteDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

@Path("/clientes")
public class ClienteResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> listarClientes(){

        List<Cliente> clientes = new ArrayList<>();

        clientes.add(new Cliente(
                "email@gmail.com",
                "senha123",
                "Nome Completo da Silva",
                "função",
                "02/02/2002",
                new Endereco("São Paulo", "Brasil")));

        clientes.add(new Cliente(
                "email2@gmail.com",
                "senha1234",
                "Nome Completo de Oliveira",
                "função 2",
                "03/03/2003",
                new Endereco("São Paulo", "Brasil")));

        return clientes;
    }

    @POST
    public void inserirCliente(DadosInsercaoClienteDTO insercaoClienteDTO){
        Cliente cliente = new Cliente(insercaoClienteDTO);
        //FAZER OS REPOSITORIES
    }

}
