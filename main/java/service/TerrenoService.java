package main.java.service;

import main.java.model.Terreno;
import main.java.repository.TerrenoRepository;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

/** Classe responsável por capturar e tratar exceções durante a comunicação entre o programa e o repositório.
 * Implementa a interface FinanciamentoService, que fornece métodos genéricos para cadastro e listagem de dados.
 */
public class TerrenoService implements FinanciamentoService<Terreno> { // Exceções tratadas
    private final TerrenoRepository Repository;
    private List<Terreno> terrenos = new ArrayList<>();

    public TerrenoService(TerrenoRepository Repository) {
        this.Repository = Repository;
    }

    /** Tenta cadastrar os dados de um terreno no repositório, capturando e tratando exceções que ocorram durante o processo.
     *
     * @param terreno Objeto da classe Terreno.
     */
    @Override
    public void cadastrarFinanciamento(Terreno terreno) { // Exceções de cadastro
        try {
            Repository.escreverDados(terreno);
        }catch (SQLException e) {
            if (e instanceof SQLSyntaxErrorException) {
                System.err.println("Erro de sintaxe no SQL: " + e.getMessage());
            } else {
                System.err.println("Erro genérico dentro do banco de dados: " + e.getMessage());
            }
        }
    }

    /** Tenta listar os dados de apartamentos no repositório, capturando e tratando exceções que ocorram durante o processo.
     *
     * @return Lista de objetos do tipo Apartamento.
     */
    @Override
    public List<Terreno> listarDados() { // Exceções de visualização
        try {
            terrenos = Repository.lerDados();
        } catch (SQLException e) {
            if (e instanceof SQLSyntaxErrorException) {
                System.err.println("Erro de sintaxe no SQL ao ler os dados: " + e.getMessage());
            } else {
                System.err.println("Erro genérico dentro do banco de dados ao ler os dados: " + e.getMessage());
            }
        }
        return terrenos;
    }
}
