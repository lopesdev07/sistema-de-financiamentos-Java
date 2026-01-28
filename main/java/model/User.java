package main.java.model;

public class User {
    private String loginCPF;
    private String senhaHash;


    public User(String loginCPF, String senha_hash) {
        this.loginCPF = loginCPF;
        this.senhaHash = senha_hash;
    }

    public String getLoginCPF() {
        return this.loginCPF;
    }
    public String getSenhaHash() {
        return this.senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

}