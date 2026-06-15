package exceptions;

public class EntradaMaiorQueValorDoImovelException extends Exception {
    public EntradaMaiorQueValorDoImovelException() {
        super("O valor da entrada não pode ser maior que o valor do imóvel.");
    }
}
