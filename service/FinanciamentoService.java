    package service;

    import java.io.IOException;
    import java.util.List;
    public interface FinanciamentoService<T> {

        void cadastrarFinanciamento(T objeto) throws IOException;

        List<T> listarDados() throws IOException ;
}
