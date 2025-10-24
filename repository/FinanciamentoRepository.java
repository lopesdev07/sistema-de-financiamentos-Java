package repository;
import java.io.IOException;
import java.util.List;

public interface FinanciamentoRepository<T> {
    void escreverDados(T objeto) throws IOException;
    List<T> lerDados() throws IOException;


}
