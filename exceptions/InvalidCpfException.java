package exceptions;

public class InvalidCpfException extends Exception {
    public InvalidCpfException(String cpf) {
        super("CPF com formato inválido, tente novamente: " + cpf);
    }
}
