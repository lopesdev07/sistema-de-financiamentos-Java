package service;

public class Sessao {
    private static Integer userId;

    public static void login(int id) {
        userId = id;
    }

    public static Integer getUserId() {
        return userId;
    }

    public static boolean isLogado() {
        return userId != null;
    }

}
