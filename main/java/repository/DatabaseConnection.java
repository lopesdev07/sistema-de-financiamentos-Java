package main.java.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Classe que gerencia o acesso ao banco de dados.
 *  É necessária para que seu método público colete corretamente os atributos de conexão.
 *
 */
public class DatabaseConnection {
    private static final String URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    /** Método para obter a conexão com o banco de dados a partir dos dados fornecidos pela classe
     *
     * @return Conexão da aplicação com o banco de dados
     * @throws SQLException Caso haja erro genérico ao acessar o banco de dados.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
