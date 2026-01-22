package main.java.view;

import main.java.model.Casa;
import java.util.InputMismatchException;
import java.util.Scanner;
import main.java.service.CasaService;
import java.util.List;

/** Classe exclusivamente responsável por interagir com o usuário para operações relacionadas a Casas.
 * Interage com o usuário e o permite realizar operações como criar e listar financiamentos de Casas.
 * Também fornece um menu específico para essas operações.
 */
public class CasaView {
    private final CasaService service;

    public CasaView(CasaService service) {
        this.service = service;
    }

    /** Cria financiamento de casa.
     *
     *@param scanner Scanner para leitura de entradas do usuário.
     */
    public void criarFinanciamentoCasa(Scanner scanner) {
        boolean funcionou = false;
        while (!funcionou) {
            try {
                System.out.println("Informe o valor do imóvel: ");
                double valorImovel = scanner.nextDouble();
                System.out.println("Informe o prazo do financiamento (em anos): ");
                int prazoFinanciamento = scanner.nextInt();
                System.out.println("Informe a taxa de juros anual (em %): ");
                double taxaJurosAnual = scanner.nextDouble() / 100;
                System.out.println("Informe o tamanho da área construída (em m²): ");
                double tamanhoAreaConstruida = scanner.nextDouble();
                System.out.println("Informe o tamanho da área do terreno (em m²): ");
                double tamanhoAreaTerreno = scanner.nextDouble();

                Casa casa = new Casa(valorImovel, prazoFinanciamento, taxaJurosAnual, 80, tamanhoAreaConstruida, tamanhoAreaTerreno);
                service.cadastrarFinanciamento(casa);
                funcionou = true;
                System.out.println("Casa inserida com sucesso!");
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido. Tente novamente.");
                scanner.nextLine();
            }
        }
    }
    /** Lista financiamentos de apartamentos, caso exista algum.
     */
    public void listarFinanciamentosCasa() {
        System.out.println("Listando financiamentos de casas:");
        List<Casa> casas = service.listarDados();
        if (casas.isEmpty()) {
            System.out.println("Nenhum financiamento de casa encontrado.");
        } else {
            for (Casa casa : casas) {
                System.out.println(casa);
            }
        }
    }
    /** Menu para interação com o usuário (casas).
     *
     *@param scanner Scanner para leitura de entradas do usuário.
     */
    public void menuCasa(Scanner scanner){
        boolean opcaoCertaCasa = false;
        while (!opcaoCertaCasa) {
            try {
                System.out.println("=== Menu Casas ===");
                System.out.println("Escolha a opção que deseja: ");
                System.out.println("1. Cadastrar financiamento de casa");
                System.out.println("2. Listar financiamentos de casas");
                System.out.println("3. Voltar ao menu principal");
                System.out.print("Escolha uma opção: ");

                switch(scanner.nextInt()) {
                    case 1:
                        criarFinanciamentoCasa(scanner);
                        break;
                    case 2:
                        listarFinanciamentosCasa();
                        break;
                    case 3:
                        opcaoCertaCasa = true;
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