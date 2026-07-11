package exceptions;

public class InvalidPasswordException extends Exception {
    public InvalidPasswordException() {
        super("The provided password is incorrect.");
    }
}