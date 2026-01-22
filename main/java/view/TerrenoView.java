package main.java.view;

import main.java.model.Terreno;
import java.util.InputMismatchException;
import java.util.Scanner;
import main.java.service.TerrenoService;

/** Classe exclusivamente responsável por interagir com o usuário para operações relacionadas a terrenos.
 * Interage com o usuário e o permite realizar operações como criar e listar financiamentos de terrenos.
 * Também fornece um menu específico para essas operações.
 */
public class TerrenoView {
    private final TerrenoService service;

    /** Cria uma nova TerrenoView com o serviço injetado.
     *
     * @param service serviço responsável pelas operações de terreno.
     */
    public TerrenoView(TerrenoService service) {
        this.service = service;
    }

    /** Cria financiamento de terreno.
     *
     * @param scanner Scanner para leitura de entradas do usuário.
     */
    public void criarFinanciamentoTerreno(Scanner scanner) {
        boolean funcionou = false;
        while (!funcionou) {
            try {
                System.out.println("Informe o valor do imóvel: ");
                double valorImovel = scanner.nextDouble();
                System.out.println("Informe o prazo do financiamento (em anos): ");
                int prazoFinanciamento = scanner.nextInt();
                System.out.println("Informe a taxa de juros anual (em %): ");
                double taxaJurosAnual = scanner.nextDouble() / 100;
                scanner.nextLine();
                System.out.println("Informe a zona localizada do terreno: ");
                String zonaLocalizada = scanner.nextLine();

                Terreno terreno = new Terreno(valorImovel, prazoFinanciamento, taxaJurosAnual, zonaLocalizada);
                service.cadastrarFinanciamento(terreno);
                funcionou = true;
                System.out.println("Terreno inserido com sucesso!");
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido. Tente novamente.");
                scanner.nextLine();
            }
        }
    }

    /** Lista financiamentos de terrenos, caso exista algum.
     */
    public void listarFinanciamentosTerreno() {
        System.out.println("Listando financiamentos de terrenos:");
        var terrenos = service.listarDados();
        if (terrenos.isEmpty()) {
            System.out.println("Nenhum financiamento de terreno encontrado.");
        } else {
            for (Terreno terreno : terrenos) {
                System.out.println(terreno);
            }
        }
    }
    /** Menu para interação com o usuário (terrenos).
     *
     * @param scanner Scanner para leitura de entradas do usuário.
     */
    public void menuTerreno(Scanner scanner){
        boolean opcaoCertaTerreno = false;
        while (!opcaoCertaTerreno) {
            try {
                System.out.println("=== Menu Terrenos ===");
                System.out.println("Escolha a opção que deseja: ");
                System.out.println("1. Cadastrar financiamento de terreno");
                System.out.println("2. Listar financiamentos de terrenos");
                System.out.println("3. Voltar ao menu principal");
                System.out.print("Escolha uma opção: ");

                switch(scanner.nextInt()) {
                    case 1:
                        criarFinanciamentoTerreno(scanner);
                        break;
                    case 2:
                        listarFinanciamentosTerreno();
                        break;
                    case 3:
                        opcaoCertaTerreno = true;
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