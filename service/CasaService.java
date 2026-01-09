package service;

import model.Casa;
import repository.CasaRepository;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

/** Classe responsável por capturar e tratar exceções durante a comunicação entre o programa e o repositório.
 * Implementa a interface FinanciamentoService, que fornece métodos genéricos para cadastro e listagem de dados.
 */
public class CasaService implements FinanciamentoService<Casa> { // Exceções tratadas
    private final CasaRepository Repository;
    private List<Casa> casas = new ArrayList<>();


    public CasaService(CasaRepository Repository) {
        this.Repository = Repository;
    }

    /** Tenta cadastrar os dados de uma casa no repositório, capturando e tratando exceções que ocorram durante o processo.
     *
     * @param casa Objeto da classe Casa.
     */
    @Override
    public void cadastrarFinanciamento(Casa casa) { // Exceções de cadastro
        try {
            Repository.escreverDados(casa);
        } catch (SQLException e) {
            if (e instanceof SQLSyntaxErrorException) {
                System.err.println("Erro de sintaxe no SQL: " + e.getMessage());
            } else {
                System.err.println("Erro genérico dentro do banco de dados: " + e.getMessage());
            }


        }
    }

    /** Tenta listar os dados de casas no repositório, capturando e tratando exceções que ocorram durante o processo.
     *
     * @return Lista de objetos do tipo Casa.
     */
    @Override
    public List<Casa> listarDados() { // Exceções de visualização
        try {
            casas = Repository.lerDados();
        } catch (SQLException e) {
            if (e instanceof SQLSyntaxErrorException) {
                System.err.println("Erro de sintaxe no SQL ao ler os dados: " + e.getMessage());
            } else {
                System.err.println("Erro genérico dentro do banco de dados ao ler os dados: " + e.getMessage());
            }

        }
        return casas;
    }
}
