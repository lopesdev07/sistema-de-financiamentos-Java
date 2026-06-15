package repository;

import model.User;
import java.sql.*;
import java.util.Optional;

public class AuthRepository {

    public Optional<User> findByCpf(String cpf) throws SQLException { // Lógica para buscar o usuário no banco de dados pelo CPF
        String sql = "SELECT user_id, login_cpf, senha_hash FROM users WHERE login_cpf = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String loginCPF = rs.getString("login_cpf");
                    String senhaHash = rs.getString("senha_hash");
                    User user = new User(userId, loginCPF, senhaHash);
                    return Optional.of(user); // Retorna o usuário encontrado
                }
        }}
        return Optional.empty(); // Retorna um Optional vazio se não encontrar
    }

    public int saveUser (User user) throws SQLException { // Lógica para salvar um novo usuário no banco de dados
        String sql = "INSERT INTO users (login_cpf, senha_hash) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getLoginCPF());
            stmt.setString(2, user.getSenhaHash());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    user.setUserId(generatedId);
                    return generatedId;
                } else {
                    throw new SQLException("Nenhuma chave gerada retornada.");
                }
        }
    }



}}

