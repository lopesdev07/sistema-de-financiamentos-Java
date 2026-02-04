package main.java.service;

public class Sessao {
    private static Integer usuarioId;

    public static void login(int id) {
        usuarioId = id;
    }

    public static Integer getUsuarioId() {
        return usuarioId;
    }

    public static boolean isLogado() {
        return usuarioId != null;
    }

}
