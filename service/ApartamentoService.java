package service;

import model.Apartamento;
import repository.ApartamentoRepository;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

/** Classe responsável por capturar e tratar exceções durante a comunicação entre o programa e o repositório.
 * Implementa a interface FinanciamentoService, que fornece métodos genéricos para cadastro e listagem de dados.
 */
public class ApartamentoService implements FinanciamentoService<Apartamento> {
    private final ApartamentoRepository Repository;
    private List<Apartamento> apartamentos = new ArrayList<>();

    public ApartamentoService(ApartamentoRepository Repository) {
        this.Repository = Repository;
    }

    /** Tenta cadastrar os dados de um apartamento no repositório, capturando e tratando exceções que ocorram durante o processo.
     *
     * @param apartamento Objeto da classe Apartamento.
     */
    @Override
    public void cadastrarFinanciamento(Apartamento apartamento) { // Exceções de cadastro
        try {
            Repository.escreverDados(apartamento);
        }

        catch (SQLException e) {
            if (e instanceof SQLSyntaxErrorException) {
                System.err.println("Erro de sintaxe no SQL: " + e.getMessage());
            }
            else {
                System.err.println("Erro genérico dentro do banco de dados: " + e.getMessage());
            }

        }
    }

    /** Tenta listar os dados de apartamentos no repositório, capturando e tratando exceções que ocorram durante o processo.
     *
     * @return Lista de objetos do tipo Apartamento.
     */

    @Override
    public List<Apartamento> listarDados() { // Exceções de listagem
        try {
            apartamentos = Repository.lerDados();
        } catch (SQLException e) {
            if (e instanceof SQLSyntaxErrorException) {
                System.err.println("Erro de sintaxe no SQL: " + e.getMessage());
            } else {
                System.err.println("Erro genérico dentro do banco de dados: " + e.getMessage());
            }
        }
        return apartamentos;
    }
}
