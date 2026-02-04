package main.java.model;

public class User {
    private String loginCPF;
    private String senhaHash;
    private int userId;

    public User(int userId, String loginCPF, String senhaHash) {
        this.userId = userId;
        this.loginCPF = loginCPF;
        this.senhaHash = senhaHash;
    }

    public String getLoginCPF() {
        return this.loginCPF;
    }
    public String getSenhaHash() {
        return this.senhaHash;
    }
    public int getUserId() {
        return this.userId;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

}