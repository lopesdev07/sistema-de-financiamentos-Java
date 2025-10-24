package view;

import model.Casa;
import java.util.InputMismatchException;
import java.util.Scanner;
import repository.CasaRepository;
import service.CasaService;

public class CasaView {
    private final CasaService service = new CasaService(new CasaRepository());
    private final Scanner scanner = new Scanner(System.in);



    public void criarFinanciamentoCasa() {
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
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido. Tente novamente.");
                scanner.nextLine();
            }
        }
    }
        public void listarFinanciamentosCasa () {
            System.out.println("Listando financiamentos de casas:");
            var casas = service.listarDados();
            if (casas.isEmpty()) {
                System.out.println("Nenhum financiamento de casa encontrado.");
            } else {
                for (Casa casa : casas) {
                    System.out.println(casa);
                }
            }
        }

    public void menuCasa(){
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
                        criarFinanciamentoCasa();;
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
