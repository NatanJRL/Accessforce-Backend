package br.com.fiap.repository;

import br.com.fiap.model.Usuario;

import java.sql.*;
import java.time.LocalDate;

public class UsuarioRepository{
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
    public Date localDateParaDateSQL(LocalDate data){
        return Date.valueOf(LocalDate.of(data.getYear(), data.getMonth(), data.getDayOfMonth()));
    }


    public Long inserirUsuario(Usuario usuario) {
        try (Connection connection = this.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "insert into t_sf_usuario(email," +
                             " senha, nm_completo, dt_registro, dt_nascimento) values(?, ?, ?, ?, ?)", new String[]{"id_usuario"})
        ) {
            preparedStatement.setString(1, usuario.getEmail());
            preparedStatement.setString(2, usuario.getSenha());
            preparedStatement.setString(3, usuario.getNomeCompleto());

            Date dataRegistro = localDateParaDateSQL(usuario.getDataDeRegistro());
            preparedStatement.setDate(4, dataRegistro);

            Date dataNascimento = localDateParaDateSQL(usuario.getDataNascimento());
            preparedStatement.setDate(5, dataNascimento);



            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()){
                Long idGerado = generatedKeys.getLong(1);
                return idGerado;
            }


            return preparedStatement.getGeneratedKeys().getLong(1);
        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}


