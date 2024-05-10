package br.com.fiap.repository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    static Connection getConnection(){
        Properties databaseProperties = readDatabaseProperties();
        String URL_ORACLE = databaseProperties.getProperty("jdbc.url");
        String USUARIO = databaseProperties.getProperty("jdbc.username");
        String SENHA = databaseProperties.getProperty("jdbc.password");

        try{
            return DriverManager.getConnection(URL_ORACLE, USUARIO, SENHA);
        }catch (SQLException exception){
            throw new RuntimeException(exception);
        }
    }

    private static Properties readDatabaseProperties(){
        try (InputStream inputStream = DBConnection.class.getClassLoader().getResourceAsStream("database.properties")){
            Properties properties = new Properties();
            properties.load(inputStream);

            return properties;
        }catch (IOException exception){
            throw new RuntimeException(exception);
        }
    }
}