package main.java.repository;
import java.sql.SQLException;
import java.util.List;

/** Interface genérica para escrita e leitura de dados, define a necessidade
 * das classes de repositório implementarem seus métodos de forma padronizada e
 * reutilizável
 *
 * @param <T> Tipo de objeto a ser definido pela classe que estende essa interface.
 */
public interface FinanciamentoRepository<T> {
    /** Método genérico para escrever dados no repositório
     *
     * @param objeto Objeto genérico a ser definido na implementação.
     * @throws SQLException Caso haja erro genérico ao acessar o banco de dados.
     */
    void escreverDados(T objeto) throws SQLException;

    /**
     * Método genérico para ler dados no repositório
     *
     * @return Lista de objetos lidos (o tipo de objeto é definido na implementação).
     * @throws SQLException Caso haja erro genérico ao acessar o banco de dados.
     */
    List<T> lerDados() throws SQLException;


}
