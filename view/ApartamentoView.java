package view;

import model.Apartamento;
import java.util.InputMismatchException;
import java.util.Scanner;
import repository.ApartamentoRepository;
import service.ApartamentoService;
import java.util.List;

/** Classe exclusivamente responsável por interagir com o usuário para operações relacionadas a apartamentos.
 * Interage com o usuário e o permite realizar operações como criar e listar financiamentos de apartamentos.
 * Também fornece um menu específico para essas operações.
 */
public class ApartamentoView { // Classe de interação com o usuário
    private final ApartamentoService service = new ApartamentoService(new ApartamentoRepository());

    /** Cria financiamento de apartamento.
     *
     * @param scanner Scanner para leitura de entradas do usuário.
     */
    public void criarFinanciamentoApartamento(Scanner scanner) {
        boolean funcionou = false;
        while (!funcionou) {
            try {
                System.out.println("Informe o valor do imóvel: ");
                double valorImovel = scanner.nextDouble();
                System.out.println("Informe o prazo do financiamento (em anos): ");
                int prazoFinanciamento = scanner.nextInt();
                System.out.println("Informe a taxa de juros anual (em %): ");
                double taxaJurosAnual = scanner.nextDouble() / 100;
                System.out.println("Informe o número do andar: ");
                int numeroAndar = scanner.nextInt();
                System.out.println("Informe a quantidade de vagas de garagem: ");
                int vagasGaragem = scanner.nextInt();

                Apartamento apto = new Apartamento(valorImovel, prazoFinanciamento, taxaJurosAnual, vagasGaragem, numeroAndar);
                service.cadastrarFinanciamento(apto);
                funcionou = true;
                System.out.println("Apartamento inserido com sucesso!");
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido. Tente novamente.");
                scanner.nextLine();
            }
        }
    }

    /** Lista financiamentos de apartamentos, caso exista algum.
     */
    public void listarFinanciamentosApartamento() {
        System.out.println("Listando financiamentos de apartamentos:");
        List<Apartamento> apartamentos = service.listarDados();
        if (apartamentos.isEmpty()) {
            System.out.println("Nenhum financiamento de apartamento encontrado.");
        } else {
            for (Apartamento apto : apartamentos) {
                System.out.println(apto);
            }
        }
    }
    /** Menu para interação com o usuário (apartamentos).
     *
     * @param scanner Scanner para leitura de entradas do usuário.
     */
    public void menuApartamento(Scanner scanner) {
        boolean opcaoCerta = false;
        while (!opcaoCerta) {
            try {
                System.out.println("=== Menu Apartamentos ===");
                System.out.println("Escolha a opção que deseja: ");
                System.out.println("1. Cadastrar financiamento de apartamento");
                System.out.println("2. Listar financiamentos de apartamentos");
                System.out.println("3. Voltar ao menu principal");
                System.out.print("Escolha uma opção: ");

                switch(scanner.nextInt()) {
                    case 1:
                        criarFinanciamentoApartamento(scanner);
                        break;
                    case 2:
                        listarFinanciamentosApartamento();
                        break;
                    case 3:
                        opcaoCerta = true;
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira um número inteiro.");
                scanner.nextLine();
            }
        }
    }
}
