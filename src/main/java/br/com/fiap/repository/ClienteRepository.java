package br.com.fiap.repository;

import br.com.fiap.ClienteResource;
import br.com.fiap.model.cliente.Cliente;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ClienteRepository{

    private static final Map<String, String> TABLE_COLUMNS = Map.of(
            "ID","id_cliente",
            "ID_DE_USUARIO", "id_usuario",
            "STATUS", "st_cliente",
            "FUNCAO", "nm_funcao"
    );


    UsuarioRepository usuarioRepository;

    public ClienteRepository(){
        this.usuarioRepository = new UsuarioRepository();
    }

    public Long inserirCliente(Cliente cliente) {
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                     "insert into t_sf_cliente(%s, %s, %s) values(?, ?, ?)"
                             .formatted(TABLE_COLUMNS.get("ID_DE_USUARIO"), TABLE_COLUMNS.get("FUNCAO"),TABLE_COLUMNS.get("STATUS")));
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
                Connection conexao = DBConnection.getConnection();
                Statement comandoSelect = conexao.createStatement()
        ){
            List<Cliente> retorno = new ArrayList<>();
            ResultSet resultadoConsulta =
                    comandoSelect.executeQuery("select * from t_sf_usuario u join t_sf_cliente c on u.%s = c.%s"
                            .formatted(TABLE_COLUMNS.get("ID_DE_USUARIO"), UsuarioRepository.TABLE_COLUMNS.get("ID")));

            while(resultadoConsulta.next()){
                Long idUsuario = resultadoConsulta.getLong(TABLE_COLUMNS.get("ID_DE_USUARIO"));
                String email = resultadoConsulta.getString(UsuarioRepository.TABLE_COLUMNS.get("EMAIL"));
                String senha = resultadoConsulta.getString(UsuarioRepository.TABLE_COLUMNS.get("SENHA"));
                String nomeCompleto = resultadoConsulta.getString(UsuarioRepository.TABLE_COLUMNS.get("NOME_COMPLETO"));

                Date dataRegistro = resultadoConsulta.getDate(UsuarioRepository.TABLE_COLUMNS.get("DATA_DE_REGISTRO"));
                Date dataNascimento = resultadoConsulta.getDate(UsuarioRepository.TABLE_COLUMNS.get("DATA_DE_NASCIMENTO"));

                Long idCliente = resultadoConsulta.getLong(TABLE_COLUMNS.get("ID"));
                String funcao = resultadoConsulta.getString(TABLE_COLUMNS.get("FUNCAO"));
                boolean statusCliente = resultadoConsulta.getBoolean(TABLE_COLUMNS.get("STATUS"));


                Cliente clienteLidoDoBanco = new Cliente(
                        idUsuario,
                        email,
                        senha,
                        nomeCompleto,
                        funcao,
                        dataRegistro.toString(),
                        dataNascimento.toString());

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

