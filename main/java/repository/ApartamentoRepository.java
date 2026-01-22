package main.java.repository;

import main.java.model.Apartamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Classe responsável por fazer a comunicação entre o programa e o banco de dados.
 * Implementa a interface FinanciamentoRepository, que fornece métodos genéricos para escrita e leitura de dados.
 */
public class ApartamentoRepository implements FinanciamentoRepository<Apartamento> {

    /** Envia um comando SQL para o banco de dados, que serve
     * para inserir os dados de um apartamento no banco de dados.
     * Deve ser usado por uma camada de serviço que lide com exceções e regras de negócio.
     *
     * @param apartamento Objeto da classe Apartamento.
     * @throws SQLException Caso haja erro genérico ao acessar o banco de dados.
     */
    @Override
    public void escreverDados(Apartamento apartamento) throws SQLException {
        String sql = "INSERT INTO apartamentos (valorImovel, prazoFinanciamento, taxaJurosAnual, vagasGaragem, numeroAndar) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, apartamento.getValorImovel());
            stmt.setInt(2, apartamento.getPrazoFinanciamento());
            stmt.setDouble(3, apartamento.getTaxaJurosAnual());
            stmt.setInt(4, apartamento.getVagasGaragem());
            stmt.setInt(5, apartamento.getNumeroAndar());

            stmt.executeUpdate();
        }
    }

    /** Lê os dados fornecidos pelo banco de dados e os converte em objetos do tipo Apartamento.
     * Durante a leitura, os objetos são criados e adicionados a uma lista, que é retornada ao final do processo.
     * Deve ser usado por uma camada de serviço que lide com exceções e regras de negócio
     *
     * @return Lista contendo todos os apartamentos cadastrados no banco de dados.
     * @throws SQLException Caso haja erro genérico ao acessar o banco de dados.
     */
    @Override
    public List<Apartamento> lerDados() throws SQLException {
        List<Apartamento> apartamentos = new ArrayList<>();

        String sql = "SELECT * FROM apartamentos";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            apartamentos.add(new Apartamento(
                    rs.getDouble("valorImovel"),
                    rs.getInt("prazoFinanciamento"),
                    rs.getDouble("taxaJurosAnual"),
                    rs.getInt("vagasGaragem"),
                    rs.getInt("numeroAndar")
            ));

            }
        }
        return apartamentos;
    }
}
