package br.com.fiap.repository;

import br.com.fiap.model.telefone.Telefone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class TelefoneRepository {

    public static final Map<String, String> TABLE_COLUMNS = Map.of(
            "ID", "id_telefone",
            "ID_DO_CLIENTE", "id_cliente",
            "NUMERO", "nmr_telefone",
            "DDD", "ddd_telefone",
            "TIPO_TELEFONE", "tp_telefone",
            "OBSERVACOES", "obs_telefone"
    );
    public void inserirTelefone(Telefone telefone, Long idCliente){
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("""
                            insert into
                            t_sf_telefone(%s, %s, %s, %s)
                            values
                            (?,?,?,?)
                            """.formatted(
                                    TABLE_COLUMNS.get("ID_DO_CLIENTE"),
                                    TABLE_COLUMNS.get("DDD"),
                                    TABLE_COLUMNS.get("NUMERO"),
                                    TABLE_COLUMNS.get("TIPO_TELEFONE")
                            ))
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
