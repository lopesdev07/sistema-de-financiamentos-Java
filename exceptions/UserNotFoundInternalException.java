package exceptions;

public class UserNotFoundInternalException extends Exception {
    public UserNotFoundInternalException() {
        super("Usuário não encontrado no banco de dados.");
    }
}
