package view;

import model.Apartamento;
import java.util.InputMismatchException;
import java.util.Scanner;
import repository.ApartamentoRepository;
import service.ApartamentoService;
import java.util.List;

public class ApartamentoView {
    private final ApartamentoService service = new ApartamentoService(new ApartamentoRepository());
    private final Scanner scanner = new Scanner(System.in);

    public void criarFinanciamentoApartamento() {
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
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido. Tente novamente.");
                scanner.nextLine();
            }
        }
    }

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

    public void menuApartamento() {
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
                        criarFinanciamentoApartamento();
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
