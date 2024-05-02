package br.com.fiap.repository;

import br.com.fiap.model.endereco.Endereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class EnderecoRepository{

    public static final Map<String, String> TABLE_COLUMNS =
            Map.of(
                    "ID_DE_ENDERECO", "id_endereco",
                    "ID_DE_USUARIO", "id_usuario",
                    "CEP", "cep",
                    "RUA", "rua",
                    "BAIRRO", "bairro",
                    "CIDADE", "cidade",
                    "ESTADO", "estado",
                    "PAIS", "pais"
            );

    public void inserirEndereco(Endereco endereco, Long idUsuario) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "insert into t_sf_endereco(id_usuario, cep, rua, bairro, cidade, estado, pais) values(?, ?, ?, ?, ?, ?, ?)");
        ) {
            preparedStatement.setLong(1, idUsuario);
            preparedStatement.setString(2, endereco.getCep());
            preparedStatement.setString(3, endereco.getRua());
            preparedStatement.setString(4, endereco.getBairro());
            preparedStatement.setString(5, endereco.getCidade());
            preparedStatement.setString(6, endereco.getEstado());
            preparedStatement.setString(7, endereco.getPais());

            preparedStatement.executeUpdate();

        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
}

