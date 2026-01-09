package repository;

import model.Terreno;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Classe responsável por fazer a comunicação entre o programa e o banco de dados.
 * Implementa a interface FinanciamentoRepository, que fornece métodos genéricos para escrita e leitura de dados.
 */
public class TerrenoRepository implements FinanciamentoRepository<Terreno> {

    /** Envia um comando SQL para o banco de dados, que serve
     * para inserir os dados de um terreno no banco de dados.
     * Deve ser usado por uma camada de serviço que lide com exceções e regras de negócio.
     *
     * @param terreno Objeto da classe Terreno.
     * @throws SQLException Caso haja erro genérico ao acessar o banco de dados.
     */
    @Override
    public void escreverDados(Terreno terreno) throws SQLException {
        String sql = "INSERT INTO terrenos (valorImovel, prazoFinanciamento, taxaJurosAnual, zonaLocalizada) VALUES (?, ?, ?, ?)";
        try  (Connection conn = DatabaseConnection.getConnection();
              PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, terreno.getValorImovel());
            stmt.setInt(2, terreno.getPrazoFinanciamento());
            stmt.setDouble(3, terreno.getTaxaJurosAnual());
            stmt.setString(4, terreno.getZonaLocalizada());

            stmt.executeUpdate();
        }
    }

    /** Lê os dados fornecidos pelo banco de dados e os converte em objetos do tipo Terreno.
     * Durante a leitura, os objetos são criados e adicionados a uma lista, que é retornada ao final do processo.
     * Deve ser usado por uma camada de serviço que lide com exceções e regras de negócio
     *
     * @return Lista contendo todos os terrenos cadastrados no banco de dados.
     * @throws SQLException Caso haja erro genérico ao acessar o banco de dados.
     */
    @Override
    public List<Terreno> lerDados() throws SQLException {
        List<Terreno> terrenos = new ArrayList<>();

        String sql = "SELECT * FROM terrenos";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                terrenos.add(new Terreno(
                        rs.getDouble("valorImovel"),
                        rs.getInt("prazoFinanciamento"),
                        rs.getDouble("taxaJurosAnual"),
                        rs.getString("zonaLocalizada")));
            }
        }
        return terrenos;
    }
}
