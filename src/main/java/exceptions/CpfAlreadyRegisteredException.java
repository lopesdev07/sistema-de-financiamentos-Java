package exceptions;

public class CpfAlreadyRegisteredException extends Exception {
    public CpfAlreadyRegisteredException(String cpf) {
        super("This CPF is already registered: " + cpf);
    }


}