package repository;

import model.User;
import java.sql.*;
import java.util.Optional;

public class AuthRepository {

    public Optional<User> findByCpf(String cpf) throws SQLException { // Logic to find the user in the database by CPF
        String sql = "SELECT user_id, login_cpf, password_hash FROM users WHERE login_cpf = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String loginCpf = rs.getString("login_cpf");
                    String passwordHash = rs.getString("password_hash");
                    User user = new User(userId, loginCpf, passwordHash);
                    return Optional.of(user);
                }
            }}
        return Optional.empty();
    }

    public int saveUser (User user) throws SQLException {
        String sql = "INSERT INTO users (login_cpf, password_hash) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getLoginCpf());
            stmt.setString(2, user.getPasswordHash());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    user.setUserId(generatedId);
                    return generatedId;
                } else {
                    throw new SQLException("No generated key was returned.");
                }
            }
        }



    }}