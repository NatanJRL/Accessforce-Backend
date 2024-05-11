package br.com.fiap.repository;

import br.com.fiap.model.empresa.Empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class EmpresaRepository {

    public static final Map<String, String> TABLE_COLUMNS = Map.of(
            "ID_DO_CLIENTE", "id_cliente",
            "ID", "id_empresa",
            "NOME_DA_EMPRESA", "nm_empresa",
            "TAMANHO_DA_EMPRESA", "tamanho"
    );
    public void inserirEmpresa(Empresa empresa, Long idCLiente){
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("""
                    insert into t_sf_empresa(%s, %s, %s) values(?,?,?)
                    """
                    .formatted(
                            TABLE_COLUMNS.get("ID_DO_CLIENTE"),
                            TABLE_COLUMNS.get("NOME_DA_EMPRESA"),
                            TABLE_COLUMNS.get("TAMANHO_DA_EMPRESA")
            ))){

            statement.setLong(1, idCLiente);
            statement.setString(2, empresa.getNome());
            statement.setString(3, empresa.getTamanhoEmpresa().toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




}
