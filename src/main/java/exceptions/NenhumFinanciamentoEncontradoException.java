package exceptions;

public class NenhumFinanciamentoEncontradoException extends Exception {
    public NenhumFinanciamentoEncontradoException(String msg) {
        super("Nenhum financiamento encontrado para este usuario.");
    }
}
