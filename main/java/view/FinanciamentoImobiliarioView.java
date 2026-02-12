package main.java.view;

import exceptions.EntradaMaiorQueValorDoImovelException;
import main.java.model.*;
import main.java.service.FinanciamentoImobiliarioService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FinanciamentoImobiliarioView {
    private FinanciamentoImobiliarioService service;

    public FinanciamentoImobiliarioView(FinanciamentoImobiliarioService service) {
        this.service = service;
    }

    public void menuFinanciamentoImobiliario(Scanner scanner) {
        System.out.println("Este é o menu de Financiamento Imobiliário.");
        System.out.println("Escolha o que deseja fazer: ");
        System.out.println("1. Simular Financiamento");
        System.out.println("2. Gerenciar Financiamentos");
        System.out.println("3. Voltar ao Menu Principal");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        switch (opcao) {
            case 1 -> menuSimulacao(scanner);
            case 2 -> menuGerenciamento(scanner);
            case 3 -> {}
            default -> System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private void menuGerenciamento(Scanner scanner) {}

    private void menuSimulacao(Scanner scanner) {
        System.out.println("Primeiro, escolha o tipo de imóvel para o qual deseja simular o financiamento:");
        System.out.println("Agora, informe os detalhes do imóvel:");
        criarFinanciamentoImobiliario(scanner);
        }


    public void criarFinanciamentoImobiliario(Scanner scanner) { // usar os dados no service
        try {
            int quartos = 0;
            int vagasGaragem = 0;
            double areaTerreno = 0.0;
            int andar = 0;
            boolean elevador = false;
            double valorCondominio = 0.0;

            TipoImovel tipoImovel = escolherTipoImovel(scanner);
            TipoAmortizacao tipoAmortizacao = escolherTipoAmortizacao(scanner);
            CondicaoImovel condicaoImovel = definirCondicaoImovel(scanner);

            System.out.print("Valor do imóvel: ");
            double valorImovel = scanner.nextDouble();

            System.out.print("Valor de entrada: ");
            double valorEntrada = scanner.nextDouble();

            scanner.nextLine();
            System.out.print("Zoneamento: ");
            String zoneamento = scanner.nextLine();

            System.out.println("Prazo em meses desejado: "); // min 240 max 360 meses, por enquanto.
            int prazoEmMeses = scanner.nextInt();

            if (tipoImovel == TipoImovel.CASA) {
                System.out.print("Quantidade de quartos: ");
                quartos = scanner.nextInt();
                System.out.print("Vagas de garagem: ");
                vagasGaragem = scanner.nextInt();
                System.out.print("Área do terreno: ");
                areaTerreno = scanner.nextDouble();
            }

            if (tipoImovel == TipoImovel.APARTAMENTO) {
                System.out.print("Andar: ");
                andar = scanner.nextInt();
                System.out.print("Tem elevador (true/false): ");
                elevador = scanner.nextBoolean();
                System.out.print("Valor do condomínio: ");
                valorCondominio = scanner.nextDouble();
            }

            if (tipoImovel == TipoImovel.TERRENO) {
                System.out.print("Área do terreno: ");
                areaTerreno = scanner.nextDouble();
            }
            FinanciamentoImobiliario financiamento =
                    service.simularFinanciamento(
                            valorImovel,
                            valorEntrada,
                            prazoEmMeses,
                            condicaoImovel,
                            tipoAmortizacao,
                            tipoImovel
                    );

            service.calcularParcelas(financiamento);

        } catch (InputMismatchException e) {
            System.err.println("Valor inválido. Tente novamente.");
            scanner.nextLine();
        } catch (EntradaMaiorQueValorDoImovelException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }


    private TipoAmortizacao escolherTipoAmortizacao(Scanner scanner) {
        System.out.println("Escolha o tipo de amortização:");
        System.out.println("1 - SAC");
        System.out.println("2 - PRICE");

        int opcao = scanner.nextInt();

        return switch (opcao) {
            case 1 -> TipoAmortizacao.SAC;
            case 2 -> TipoAmortizacao.PRICE;
            default -> throw new IllegalArgumentException("Opção inválida");
        };
    }

    private TipoImovel escolherTipoImovel(Scanner scanner) {
        System.out.println("1 - Casa");
        System.out.println("2 - Apartamento");
        System.out.println("3 - Terreno");

        int opcao = scanner.nextInt();

        return switch (opcao) {
            case 1 -> TipoImovel.CASA;
            case 2 -> TipoImovel.APARTAMENTO;
            case 3 -> TipoImovel.TERRENO;
            default -> throw new IllegalArgumentException("Opção inválida");
        };
    }

    private CondicaoImovel definirCondicaoImovel(Scanner scanner) {
        System.out.println("Informe a condição atual do imóvel:");
        System.out.println("1 - Novo");
        System.out.println("2 - Usado");

        int opcao = scanner.nextInt();

        return switch (opcao) {
            case 1 -> CondicaoImovel.NOVO;
            case 2 -> CondicaoImovel.USADO;
            default -> throw new IllegalArgumentException("Opção inválida");
        };
    }

    private void mostrarSimulacaoFinanciamentoImobiliario(FinanciamentoImobiliario fin) {
        System.out.println("Aqui estão os detalhes da sua simulação de financiamento imobiliário:");
        System.out.println(fin.toString());

    }
}