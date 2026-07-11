package exceptions;

public class InvalidCpfException extends Exception {
    public InvalidCpfException(String cpf) {
        super("Invalid CPF format, please try again: " + cpf);
    }
}