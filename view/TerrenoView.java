package view;

import model.Terreno;
import java.util.InputMismatchException;
import java.util.Scanner;
import repository.TerrenoRepository;
import service.TerrenoService;

public class TerrenoView {
    private final TerrenoService service = new TerrenoService(new TerrenoRepository());
    private final Scanner scanner = new Scanner(System.in);

    public void criarFinanciamentoTerreno() {
        boolean funcionou = false;
        while (!funcionou) {
            try {
                System.out.println("Informe o valor do imóvel: ");
                double valorImovel = scanner.nextDouble();
                System.out.println("Informe o prazo do financiamento (em anos): ");
                int prazoFinanciamento = scanner.nextInt();
                System.out.println("Informe a taxa de juros anual (em %): ");
                double taxaJurosAnual = scanner.nextDouble() / 100;
                scanner.nextLine(); // consumir newline antes de ler string
                System.out.println("Informe a zona localizada do terreno: ");
                String zonaLocalizada = scanner.nextLine();

                Terreno terreno = new Terreno(valorImovel, prazoFinanciamento, taxaJurosAnual, zonaLocalizada);
                service.cadastrarFinanciamento(terreno);
                funcionou = true;
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido. Tente novamente.");
                scanner.nextLine();
            }
        }
    }

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

    public void menuTerreno(){
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
                        criarFinanciamentoTerreno();
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
