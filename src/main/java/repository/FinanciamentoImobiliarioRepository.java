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
                (valor_financiado, prazo_meses, taxa_juros_anual, tipo_amortizacao, tipo_imovel, status, user_id, quartos, vagas_garagem, area_terreno, andar, elevador, valor_condominio, zoneamento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setBigDecimal(1, financiamento.getValorFinanciado());
            stmt.setInt(2, financiamento.getPrazoEmMeses());
            stmt.setBigDecimal(3, financiamento.getTaxaJurosAnual());
            stmt.setString(4, financiamento.getTipoAmortizacao().name());
            stmt.setString(5, financiamento.getTipoImovel().name());
            stmt.setString(6, financiamento.getFinanciamentoStatus().name());
            stmt.setInt(7, financiamento.getUserId());
            stmt.setObject(8, financiamento.getQuartos(), java.sql.Types.INTEGER);
            stmt.setObject(9, financiamento.getVagasGaragem(), java.sql.Types.INTEGER);
            stmt.setObject(10, financiamento.getAreaTerreno(), java.sql.Types.DECIMAL);
            stmt.setObject(11, financiamento.getAndar(), java.sql.Types.INTEGER);
            stmt.setObject(12, financiamento.getElevador(), java.sql.Types.BOOLEAN);
            stmt.setObject(13, financiamento.getValorCondominio(), java.sql.Types.DECIMAL);
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
                                    rs.getBigDecimal("valor_financiado"),
                                    rs.getInt("prazo_meses"),
                                    rs.getBigDecimal("taxa_juros_anual"),
                                    TipoAmortizacao.valueOf(rs.getString("tipo_amortizacao")),
                                    TipoImovel.valueOf(rs.getString("tipo_imovel")),
                                    FinanciamentoStatus.valueOf(rs.getString("status")),
                                    Sessao.getUserId()
                            );
                    financiamento.setFinID(rs.getInt("id_financiamento"));
                    financiamentos.add(financiamento);
                }
            }
        }

        return financiamentos;
    }
    public FinanciamentoImobiliario buscarFinPorId(int idFinanciamento) throws SQLException {
        String sql = """
                SELECT id_financiamento,
                valor_financiado,
                prazo_meses,
                taxa_juros_anual,
                tipo_amortizacao,
                tipo_imovel,
                status,
                quartos,
                vagas_garagem,
                area_terreno,
                andar,
                elevador,
                valor_condominio,
                zoneamento
                FROM financiamentos_imobiliarios
                WHERE id_financiamento = ?
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFinanciamento);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    TipoImovel tipoImovel = TipoImovel.valueOf(rs.getString("tipo_imovel"));
                    TipoAmortizacao tipoAmortizacao = TipoAmortizacao.valueOf(rs.getString("tipo_amortizacao"));
                    FinanciamentoStatus status = FinanciamentoStatus.valueOf(rs.getString("status"));

                    FinanciamentoImobiliario financiamento = new FinanciamentoImobiliario(
                            rs.getBigDecimal("valor_financiado"),
                            rs.getInt("prazo_meses"),
                            rs.getBigDecimal("taxa_juros_anual"),
                            tipoAmortizacao,
                            tipoImovel,
                            status,
                            rs.getObject("quartos", Integer.class),
                            rs.getObject("vagas_garagem", Integer.class),
                            rs.getBigDecimal("area_terreno"),
                            rs.getObject("andar", Integer.class),
                            rs.getObject("elevador", Boolean.class),
                            rs.getBigDecimal("valor_condominio"),
                            rs.getString("zoneamento"),
                            Sessao.getUserId()
                    );
                    financiamento.setFinID(rs.getInt("id_financiamento"));
                    return financiamento;
                }
            }
        }
        return null;
    }
    public void editarFinanciamento(FinanciamentoImobiliario financiamento) throws SQLException {
        String sql = """
                UPDATE financiamentos_imobiliarios
                SET valor_financiado = ?,
                    prazo_meses = ?,
                    taxa_juros_anual = ?,
                    tipo_amortizacao = ?,
                    tipo_imovel = ?,
                    status = ?,
                    quartos = ?,
                    vagas_garagem = ?,
                    area_terreno = ?,
                    andar = ?,
                    elevador = ?,
                    valor_condominio = ?,
                    zoneamento = ?
                WHERE id_financiamento = ?
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBigDecimal(1, financiamento.getValorFinanciado());
            stmt.setInt(2, financiamento.getPrazoEmMeses());
            stmt.setBigDecimal(3, financiamento.getTaxaJurosAnual());
            stmt.setString(4, financiamento.getTipoAmortizacao().name());
            stmt.setString(5, financiamento.getTipoImovel().name());
            stmt.setString(6, financiamento.getFinanciamentoStatus().name());
            stmt.setObject(7, financiamento.getQuartos(), java.sql.Types.INTEGER);
            stmt.setObject(8, financiamento.getVagasGaragem(), java.sql.Types.INTEGER);
            stmt.setObject(9, financiamento.getAreaTerreno(), java.sql.Types.DECIMAL);
            stmt.setObject(10, financiamento.getAndar(), java.sql.Types.INTEGER);
            stmt.setObject(11, financiamento.getElevador(), java.sql.Types.BOOLEAN);
            stmt.setObject(12, financiamento.getValorCondominio(), java.sql.Types.DECIMAL);
            stmt.setString(13, financiamento.getZoneamento());
            stmt.setInt(14, financiamento.getFinID());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {}
             else {
                throw new SQLException("Nenhum registro atualizado. Verifique o ID do financiamento.");
            }
        }
    }

}
