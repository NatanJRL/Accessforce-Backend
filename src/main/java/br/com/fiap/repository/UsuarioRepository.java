package br.com.fiap.repository;

import br.com.fiap.model.usuario.Usuario;

import java.sql.*;
import java.time.LocalDate;
import java.util.Map;

public class UsuarioRepository{

    EnderecoRepository enderecoRepository = new EnderecoRepository();

    public final static Map<String, String> TABLE_COLUMNS = Map.of(
            "ID", "id_usuario",
            "EMAIL", "email",
            "NOME_COMPLETO", "nm_completo",
            "SENHA", "senha",
            "DATA_DE_REGISTRO", "dt_registro",
            "DATA_DE_NASCIMENTO", "dt_nascimento"

    );
    public Date localDateParaDateSQL(LocalDate data){
        return Date.valueOf(LocalDate.of(data.getYear(), data.getMonth(), data.getDayOfMonth()));
    }

    public Long inserirUsuario(Usuario usuario) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "insert into t_sf_usuario(%s, %s, %s, %s, %s) values(?, ?, ?, ?, ?)"
                                     .formatted(
                                             TABLE_COLUMNS.get("EMAIL"),
                                             TABLE_COLUMNS.get("SENHA"),
                                             TABLE_COLUMNS.get("NOME_COMPLETO"),
                                             TABLE_COLUMNS.get("DATA_DE_REGISTRO"),
                                             TABLE_COLUMNS.get("DATA_DE_NASCIMENTO")
                                     ), new String[]{"id_usuario"})
        ) {


            preparedStatement.setString(1, usuario.getEmail());
            preparedStatement.setString(2, usuario.getSenha());
            preparedStatement.setString(3, usuario.getNomeCompleto());

            Date dataRegistro = localDateParaDateSQL(usuario.getDataDeRegistro());
            preparedStatement.setDate(4, dataRegistro);

            Date dataNascimento = localDateParaDateSQL(usuario.getDataNascimento());
            preparedStatement.setDate(5, dataNascimento);

            preparedStatement.executeUpdate();

            if (preparedStatement.getGeneratedKeys().next()){
                return preparedStatement.getGeneratedKeys().getLong(1);
            }

            return null;
        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void atualizarUsuario(Usuario usuario){
        try(Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("""
                        update t_sf_usuario
                        set %s = ?, %s = ?, %s = ?,
                        %s = ?, %s = ?
                        where id_usuario = ?
                     """.formatted(
                                TABLE_COLUMNS.get("EMAIL"),
                                TABLE_COLUMNS.get("NOME_COMPLETO"),
                                TABLE_COLUMNS.get("SENHA"),
                                TABLE_COLUMNS.get("DATA_DE_REGISTRO"),
                                TABLE_COLUMNS.get("DATA_DE_NASCIMENTO")
                     )
             )){

             preparedStatement.setString(1, usuario.getEmail());
             preparedStatement.setString(2, usuario.getNomeCompleto());
             preparedStatement.setString(3, usuario.getSenha());
             preparedStatement.setDate(4, localDateParaDateSQL(usuario.getDataDeRegistro()));
             preparedStatement.setDate(5,localDateParaDateSQL( usuario.getDataNascimento()));
             preparedStatement.setLong(6, usuario.getId());

             preparedStatement.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}


