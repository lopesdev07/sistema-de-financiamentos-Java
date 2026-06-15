package exceptions;

public class AuthenticationFailedException extends Exception {
    public AuthenticationFailedException() {
        super("Login falhou: CPF ou senha incorretos.");
    }
}
