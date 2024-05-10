package br.com.fiap.repository;

import br.com.fiap.model.telefone.Telefone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TelefoneRepository {

    public void inserirTelefone(Telefone telefone, Long idCliente){
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("""
                            insert into
                            t_sf_telefone(id_cliente, ddd_telefone, nmr_telefone, tp_telefone)
                            values
                            (?,?,?,?)
                            """)
                ) {
            statement.setLong(1, idCliente);
            statement.setInt(2, telefone.getDdd());
            statement.setString(3, telefone.getNumero());
            statement.setString(4, telefone.getTipoTelefone().toString());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
