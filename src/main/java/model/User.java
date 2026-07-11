package model;

public class User {
    private String loginCpf;
    private String passwordHash;
    private int userId;

    public User(int userId, String loginCpf, String passwordHash) {
        this.userId = userId;
        this.loginCpf = loginCpf;
        this.passwordHash = passwordHash;
    }

    public String getLoginCpf() {
        return this.loginCpf;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}