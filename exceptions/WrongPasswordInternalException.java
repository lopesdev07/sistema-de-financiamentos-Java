package exceptions;

public class WrongPasswordInternalException extends Exception {
    public WrongPasswordInternalException() {
        super("A senha fornecida está incorreta.");
    }
}
