package exceptions;

public class AuthenticationFailedException extends Exception {
    public AuthenticationFailedException() {
        super("Login failed: incorrect CPF or password.");
    }
}