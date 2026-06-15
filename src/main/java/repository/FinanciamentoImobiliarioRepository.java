package repository;

import model.FinanciamentoImobiliario;
import model.FinanciamentoStatus;
import model.TipoAmortizacao;
import model.TipoImovel;
import service.Sessao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FinanciamentoImobiliarioRepository {

    public void salvarFinanciamento(FinanciamentoImobiliario financiamento) throws SQLException {

        String sql = """
                INSERT INTO financiamentos_imobiliarios
                (valor_financiado, prazo_meses, taxa_juros_anual, tipo_amortizacao, tipo_imovel, status, user_id, quartos, vagas_garagem, area_terreno, andar, elevador, valor_condominio, zoneamento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setDouble(1, financiamento.getValorFinanciado());
            stmt.setInt(2, financiamento.getPrazoEmMeses());
            stmt.setDouble(3, financiamento.getTaxaJurosAnual());
            stmt.setString(4, financiamento.getTipoAmortizacao().name());
            stmt.setString(5, financiamento.getTipoImovel().name());
            stmt.setString(6, financiamento.getFinanciamentoStatus().name());
            stmt.setInt(7, financiamento.getUserId());
            stmt.setInt(8, financiamento.getQuartos());
            stmt.setInt(9, financiamento.getVagasGaragem());
            stmt.setDouble(10, financiamento.getAreaTerreno());
            stmt.setInt(11, financiamento.getAndar());
            stmt.setBoolean(12, financiamento.getElevador());
            stmt.setDouble(13, financiamento.getValorCondominio());
            stmt.setString(14, financiamento.getZoneamento());

            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {

                if (rs.next()) {
                    financiamento.setFinID(rs.getInt(1));
                }

            }

        }
    }

    public List<FinanciamentoImobiliario> buscarFinanciamentosPorUsuario() throws SQLException {

        String sql = """
        SELECT id_financiamento,
               valor_financiado,
               prazo_meses,
               taxa_juros_anual,
               tipo_amortizacao,
               tipo_imovel,
               status
        FROM financiamentos_imobiliarios
        WHERE user_id = ?
    """;

        List<FinanciamentoImobiliario> financiamentos = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, Sessao.getUserId());

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    FinanciamentoImobiliario financiamento =
                            new FinanciamentoImobiliario(
                                    rs.getDouble("valor_financiado"),
                                    rs.getInt("prazo_meses"),
                                    rs.getDouble("taxa_juros_anual"),
                                    TipoAmortizacao.valueOf(
                                            rs.getString("tipo_amortizacao")
                                    ),
                                    FinanciamentoStatus.valueOf(
                                            rs.getString("status")
                                    ),
                                    Sessao.getUserId()
                            );
                    financiamento.setTipoImovel(TipoImovel.valueOf(rs.getString("tipo_imovel")));
                    financiamento.setFinID(rs.getInt("id_financiamento"));
                    financiamentos.add(financiamento);
                }
            }
        }

        return financiamentos;
    }
}