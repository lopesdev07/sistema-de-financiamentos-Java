package exceptions;

public class CpfAlreadyExistsException extends Exception {
    public CpfAlreadyExistsException(String cpf) {
        super("O CPF já está cadastrado: " + cpf);
    }


}
