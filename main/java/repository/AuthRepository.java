package main.java.repository;

import main.java.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public void saveUser (User user) throws SQLException { // Lógica para salvar um novo usuário no banco de dados
        String sql = "INSERT INTO users (login_cpf, senha_hash) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getLoginCPF());
            stmt.setString(2, user.getSenhaHash());

            stmt.executeUpdate();
        }
    }



}

