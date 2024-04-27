package br.com.fiap.repository;

import br.com.fiap.model.cliente.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository{
    UsuarioRepository usuarioRepository = new UsuarioRepository();
    private Connection getConnection(){
        String url = "jdbc:oracle:thin:@//oracle.fiap.com.br:1521/ORCL";
        String usuario = "rm552626";
        String senha = "080305";

        try{
            return DriverManager.getConnection(url, usuario, senha);
        }catch (SQLException exception){
            throw new RuntimeException(exception);
        }
    }

    public Long inserirCliente(Cliente cliente) {
        try (Connection connection = this.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "insert into t_sf_cliente(id_usuario, nm_funcao, st_cliente) values(?, ?, ?)");
        ) {
            Long idUsuario = usuarioRepository.inserirUsuario(cliente);
            preparedStatement.setLong(1, idUsuario);
            preparedStatement.setString(2, cliente.getFuncao());
            preparedStatement.setBoolean(3, cliente.isAtivo());
            preparedStatement.executeUpdate();
            return idUsuario;
        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Cliente> listarTodos() {
        try (
                Connection conexao = this.getConnection();
                Statement comandoSelect = conexao.createStatement()
        ){
            List<Cliente> retorno = new ArrayList<>();
            ResultSet resultadoConsulta =
                    comandoSelect.executeQuery("select * from t_sf_usuario u join t_sf_cliente c on u.id_usuario = c.id_usuario");

            while(resultadoConsulta.next()){
                Long idUsuario = resultadoConsulta.getLong("id_usuario");
                String email = resultadoConsulta.getString("email");
                String senha = resultadoConsulta.getString("senha");
                String nomeCompleto = resultadoConsulta.getString("nm_completo");

                Date dataRegistro = resultadoConsulta.getDate("dt_registro");
                Date dataNascimento = resultadoConsulta.getDate("dt_nascimento");

                Long idCliente = resultadoConsulta.getLong("id_cliente");
                String funcao = resultadoConsulta.getString("nm_funcao");
                boolean statusCliente = resultadoConsulta.getBoolean("st_cliente");


                Cliente clienteLidoDoBanco = new Cliente(idUsuario, email, senha, nomeCompleto, funcao, dataRegistro.toString(), dataNascimento.toString());
                retorno.add(clienteLidoDoBanco);
            }
            if (retorno.isEmpty()){
                return null;
            }
            return retorno;
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }
}

