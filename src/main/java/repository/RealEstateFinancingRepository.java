package repository;

import model.RealEstateFinancing;
import model.FinancingStatus;
import model.AmortizationType;
import model.PropertyType;
import service.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RealEstateFinancingRepository {

    public void saveFinancing(RealEstateFinancing financing) throws SQLException {

        String sql = """
                INSERT INTO financiamentos_imobiliarios
                (valor_financiado, prazo_meses, taxa_juros_anual, tipo_amortizacao, tipo_imovel, status, user_id, quartos, vagas_garagem, area_terreno, andar, elevador, valor_condominio, zoneamento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setBigDecimal(1, financing.getFinancedAmount());
            stmt.setInt(2, financing.getLoanTermInMonths());
            stmt.setBigDecimal(3, financing.getAnnualInterestRate());
            stmt.setString(4, financing.getAmortizationType().name());
            stmt.setString(5, financing.getPropertyType().name());
            stmt.setString(6, financing.getStatus().name());
            stmt.setInt(7, financing.getUserId());
            stmt.setObject(8, financing.getBedrooms(), java.sql.Types.INTEGER);
            stmt.setObject(9, financing.getParkingSpaces(), java.sql.Types.INTEGER);
            stmt.setObject(10, financing.getLandArea(), java.sql.Types.DECIMAL);
            stmt.setObject(11, financing.getFloor(), java.sql.Types.INTEGER);
            stmt.setObject(12, financing.hasElevator(), java.sql.Types.BOOLEAN);
            stmt.setObject(13, financing.getCondominiumFee(), java.sql.Types.DECIMAL);
            stmt.setString(14, financing.getZoning());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {

                if (rs.next()) {
                    financing.setFinancingId(rs.getInt(1));
                }

            }

        }
    }

    public List<RealEstateFinancing> findAllByUser() throws SQLException {

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

        List<RealEstateFinancing> financings = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, Session.getUserId());

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    RealEstateFinancing financing =
                            new RealEstateFinancing(
                                    rs.getBigDecimal("valor_financiado"),
                                    rs.getInt("prazo_meses"),
                                    rs.getBigDecimal("taxa_juros_anual"),
                                    AmortizationType.valueOf(rs.getString("tipo_amortizacao")),
                                    PropertyType.valueOf(rs.getString("tipo_imovel")),
                                    FinancingStatus.valueOf(rs.getString("status")),
                                    Session.getUserId()
                            );

                    financing.setFinancingId(rs.getInt("id_financiamento"));
                    financings.add(financing);
                }
            }
        }

        return financings;
    }

    public RealEstateFinancing findById(int financingId) throws SQLException {

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

            stmt.setInt(1, financingId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    PropertyType propertyType = PropertyType.valueOf(rs.getString("tipo_imovel"));
                    AmortizationType amortizationType = AmortizationType.valueOf(rs.getString("tipo_amortizacao"));
                    FinancingStatus status = FinancingStatus.valueOf(rs.getString("status"));

                    RealEstateFinancing financing = new RealEstateFinancing(
                            rs.getBigDecimal("valor_financiado"),
                            rs.getInt("prazo_meses"),
                            rs.getBigDecimal("taxa_juros_anual"),
                            amortizationType,
                            propertyType,
                            status,
                            rs.getObject("quartos", Integer.class),
                            rs.getObject("vagas_garagem", Integer.class),
                            rs.getBigDecimal("area_terreno"),
                            rs.getObject("andar", Integer.class),
                            rs.getObject("elevador", Boolean.class),
                            rs.getBigDecimal("valor_condominio"),
                            rs.getString("zoneamento"),
                            Session.getUserId()
                    );

                    financing.setFinancingId(rs.getInt("id_financiamento"));

                    return financing;
                }
            }
        }

        return null;
    }

    public void updateFinancing(RealEstateFinancing financing) throws SQLException {

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

            stmt.setBigDecimal(1, financing.getFinancedAmount());
            stmt.setInt(2, financing.getLoanTermInMonths());
            stmt.setBigDecimal(3, financing.getAnnualInterestRate());
            stmt.setString(4, financing.getAmortizationType().name());
            stmt.setString(5, financing.getPropertyType().name());
            stmt.setString(6, financing.getStatus().name());
            stmt.setObject(7, financing.getBedrooms(), java.sql.Types.INTEGER);
            stmt.setObject(8, financing.getParkingSpaces(), java.sql.Types.INTEGER);
            stmt.setObject(9, financing.getLandArea(), java.sql.Types.DECIMAL);
            stmt.setObject(10, financing.getFloor(), java.sql.Types.INTEGER);
            stmt.setObject(11, financing.hasElevator(), java.sql.Types.BOOLEAN);
            stmt.setObject(12, financing.getCondominiumFee(), java.sql.Types.DECIMAL);
            stmt.setString(13, financing.getZoning());
            stmt.setInt(14, financing.getFinancingId());

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated == 0) {
                throw new SQLException("No financing record was updated. Please verify the financing ID.");
            }
        }
    }

}