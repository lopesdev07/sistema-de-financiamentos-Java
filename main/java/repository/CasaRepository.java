package main.java.repository;
import main.java.model.Casa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;

/** Classe responsável por fazer a comunicação entre o programa e o banco de dados.
 * Implementa a interface FinanciamentoRepository, que fornece métodos genéricos para escrita e leitura de dados.
 */
public class CasaRepository implements FinanciamentoRepository<Casa> {

    /** Envia um comando SQL para o banco de dados, que serve
     * para inserir os dados de uma casa no banco de dados.
     * Deve ser usado por uma camada de serviço que lide com exceções e regras de negócio.
     *
     * @param casa Objeto da classe Classe.
     * @throws SQLException Caso haja erro genérico ao acessar o banco de dados.
     */
    @Override
    public void escreverDados(Casa casa) throws SQLException {
        String sql = "INSERT INTO casas (valorImovel, prazoFinanciamento, taxaJurosAnual, valorFixo, tamanhoAreaConstruida, tamanhoAreaTerreno) VALUES (?, ?, ?, ?, ?, ?)";
        try  (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, casa.getValorImovel());
            stmt.setInt(2, casa.getPrazoFinanciamento());
            stmt.setDouble(3, casa.getTaxaJurosAnual());
            stmt.setInt(4, casa.getValorFixo());
            stmt.setDouble(5, casa.getTamanhoAreaConstruida());
            stmt.setDouble(6, casa.getTamanhoAreaTerreno());

            stmt.executeUpdate();

            }
    }

    /** Lê os dados fornecidos pelo banco de dados e os converte em objetos do tipo Casa.
     * Durante a leitura, os objetos são criados e adicionados a uma lista, que é retornada ao final do processo.
     * Deve ser usado por uma camada de serviço que lide com exceções e regras de negócio
     *
     * @return Lista contendo todas as casas cadastradas no banco de dados.
     * @throws SQLException Caso haja erro genérico ao acessar o banco de dados.
     */
    @Override
        public List<Casa> lerDados() throws SQLException {
            List<Casa> casas = new ArrayList<>();
            String sql = "SELECT * FROM casas";

            try  (Connection conn = DatabaseConnection.getConnection();
                  PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    casas.add(new Casa(
                            rs.getDouble("valorImovel"),
                            rs.getInt("prazoFinanciamento"),
                            rs.getDouble("taxaJurosAnual"),
                            rs.getInt("valorFixo"),
                            rs.getDouble("tamanhoAreaConstruida"),
                            rs.getDouble("tamanhoAreaTerreno")));
                }

            }
            return casas;
        }

}

